/*
 * Copyright 2017 Google LLC
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

import app.tivi.data.compoundmodels.EntryWithShow
import app.tivi.data.models.Entry

/**
 * This interface represents a DAO which contains entities which are part of a single collective list.
 */
interface EntryDao<EC : Entry, LI : EntryWithShow<EC>> : EntityDao<EC> {
    fun deleteAll()
}
