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
import app.cash.sqldelight.coroutines.mapToList
import app.tivi.data.Database
import app.tivi.data.models.ShowTmdbImage
import app.tivi.util.AppCoroutineDispatchers
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class SqlDelightShowImagesDao(
    override val db: Database,
    private val dispatchers: AppCoroutineDispatchers,
) : ShowTmdbImagesDao, SqlDelightEntityDao<ShowTmdbImage> {
    override fun deleteForShowId(showId: Long) {
        db.show_imagesQueries.deleteForShowId(showId)
    }

    override fun imageCountForShowId(showId: Long): Int {
        return db.show_imagesQueries.getImageCountForShowId(showId)
            .executeAsOne()
            .toInt()
    }

    override fun getImagesForShowId(showId: Long): Flow<List<ShowTmdbImage>> {
        return db.show_imagesQueries.getImagesForShowId(showId, ::ShowTmdbImage)
            .asFlow()
            .mapToList(dispatchers.io)
    }

    override fun deleteAll() {
        db.show_imagesQueries.deleteAll()
    }

    override fun deleteEntity(entity: ShowTmdbImage) {
        db.show_imagesQueries.delete(entity.id)
    }

    override fun insert(entity: ShowTmdbImage): Long {
        db.show_imagesQueries.insert(
            id = entity.id,
            show_id = entity.showId,
            path = entity.path,
            type = entity.type,
            lang = entity.language,
            rating = entity.rating,
            is_primary = entity.isPrimary,
        )
        return db.show_imagesQueries.lastInsertRowId().executeAsOne()
    }

    override fun update(entity: ShowTmdbImage) {
        db.show_imagesQueries.update(
            id = entity.id,
            show_id = entity.showId,
            path = entity.path,
            type = entity.type,
            lang = entity.language,
            rating = entity.rating,
            is_primary = entity.isPrimary,
        )
    }
}
