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

package app.tivi.data.mappers

import app.moviebase.trakt.model.TraktSeason
import app.tivi.data.models.Season
import me.tatarka.inject.annotations.Inject

@Inject
class TraktSeasonToSeason : Mapper<TraktSeason, Season> {

    override fun map(from: TraktSeason) = Season(
        showId = 0,
        traktId = from.ids?.trakt,
        tmdbId = from.ids?.tmdb,
        number = from.number,
        title = from.title,
        summary = from.overview,
        traktRating = from.rating?.toFloat() ?: 0f,
        traktRatingVotes = from.votes ?: 0,
        episodeCount = from.episodeCount,
        episodesAired = from.airedEpisodes,
        network = from.network,
    )
}
