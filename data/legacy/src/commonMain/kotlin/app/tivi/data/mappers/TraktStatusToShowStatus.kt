/*
 * Copyright 2019 Google LLC
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

import app.moviebase.trakt.model.TraktShowStatus
import app.tivi.data.models.ShowStatus
import me.tatarka.inject.annotations.Inject

@Inject
class TraktStatusToShowStatus : Mapper<TraktShowStatus, ShowStatus> {

    override fun map(from: TraktShowStatus): ShowStatus = when (from) {
        TraktShowStatus.ENDED -> ShowStatus.ENDED
        TraktShowStatus.RETURNING_SERIES -> ShowStatus.RETURNING
        TraktShowStatus.CANCELED -> ShowStatus.CANCELED
        TraktShowStatus.IN_PRODUCTION -> ShowStatus.IN_PRODUCTION
        TraktShowStatus.PLANNED -> ShowStatus.PLANNED
    }
}
