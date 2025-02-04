/*
 * Copyright 2018 Google LLC
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

import app.tivi.data.compoundmodels.EpisodeWithSeason
import app.tivi.data.models.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface EpisodesDao : EntityDao<Episode> {

    fun episodesWithSeasonId(seasonId: Long): List<Episode>

    fun deleteWithSeasonId(seasonId: Long)

    fun episodeWithTraktId(traktId: Int): Episode?

    fun episodeWithTmdbId(tmdbId: Int): Episode?

    fun episodeWithId(id: Long): Episode?

    fun episodeTraktIdForId(id: Long): Int?

    fun episodeIdWithTraktId(traktId: Int): Long?

    fun episodeWithIdObservable(id: Long): Flow<EpisodeWithSeason>

    fun showIdForEpisodeId(episodeId: Long): Long

    fun observeNextEpisodeToWatch(showId: Long): Flow<EpisodeWithSeason?> = emptyFlow()
}
