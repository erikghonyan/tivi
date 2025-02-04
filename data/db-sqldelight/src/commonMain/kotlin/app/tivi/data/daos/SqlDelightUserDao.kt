/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.tivi.data.daos

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import app.tivi.data.Database
import app.tivi.data.models.TraktUser
import app.tivi.util.AppCoroutineDispatchers
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class SqlDelightUserDao(
    override val db: Database,
    private val dispatchers: AppCoroutineDispatchers,
) : UserDao, SqlDelightEntityDao<TraktUser> {

    override fun insert(entity: TraktUser): Long {
        db.usersQueries.insert(
            id = entity.id,
            username = entity.username,
            name = entity.name,
            joined_date = entity.joined,
            location = entity.location,
            about = entity.about,
            avatar_url = entity.avatarUrl,
            vip = entity.vip,
            is_me = entity.isMe,
        )
        return db.usersQueries.lastInsertRowId().executeAsOne()
    }

    override fun update(entity: TraktUser) {
        db.usersQueries.update(
            id = entity.id,
            username = entity.username,
            name = entity.name,
            joined_date = entity.joined,
            location = entity.location,
            about = entity.about,
            avatar_url = entity.avatarUrl,
            vip = entity.vip,
            is_me = entity.isMe,
        )
    }

    override fun observeMe(): Flow<TraktUser?> {
        return db.usersQueries.getEntryForMe(::TraktUser)
            .asFlow()
            .mapToOneOrNull(dispatchers.io)
    }

    override fun observeTraktUser(username: String): Flow<TraktUser?> {
        return db.usersQueries.getEntryForUsername(username, ::TraktUser)
            .asFlow()
            .mapToOneOrNull(dispatchers.io)
    }

    override fun getUser(username: String): TraktUser? = when (username) {
        "me" -> db.usersQueries.getEntryForMe(::TraktUser).executeAsOneOrNull()
        else -> {
            db.usersQueries.getEntryForUsername(username, ::TraktUser)
                .executeAsOneOrNull()
        }
    }

    override fun getId(username: String): Long? = when (username) {
        "me" -> db.usersQueries.idForMe().executeAsOneOrNull()
        else -> db.usersQueries.idForUsername(username).executeAsOneOrNull()
    }

    override fun deleteWithUsername(username: String) {
        db.usersQueries.deleteWithUsername(username)
    }

    override fun deleteMe() {
        db.usersQueries.deleteMe()
    }

    override fun deleteAll() {
        db.usersQueries.deleteAll()
    }

    override fun deleteEntity(entity: TraktUser) {
        db.usersQueries.deleteWithId(entity.id)
    }
}
