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


plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
    alias(libs.plugins.cacheFixPlugin)
}

// https://github.com/cashapp/multiplatform-paging/issues/6
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>> {
    compilerOptions.freeCompilerArgs.add("-opt-in=androidx.paging.ExperimentalPagingApi")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.base)

                api(projects.data.models)
                implementation(projects.data.db) // remove this eventually
                api(projects.data.legacy) // remove this eventually

                api(projects.tasks.api)

                api(projects.data.episodes)
                api(projects.data.followedshows)
                api(projects.data.popularshows)
                api(projects.data.recommendedshows)
                api(projects.data.relatedshows)
                api(projects.data.search)
                api(projects.data.showimages)
                api(projects.data.shows)
                api(projects.data.traktauth)
                api(projects.data.traktusers)
                api(projects.data.trendingshows)
                api(projects.data.watchedshows)

                implementation(projects.api.tmdb)

                api(libs.cashapp.paging.common)

                implementation(libs.kotlininject.runtime)
            }
        }
    }
}

dependencies {
    add("kspJvm", libs.kotlininject.compiler)
}
