/*
 * Copyright 2022 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import com.android.build.gradle.LibraryExtension
import com.blank.wallpaper.configureKotlinAndroid
import com.blank.wallpaper.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import java.io.File
import java.io.FileInputStream
import java.util.Properties

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val apikeyPropertiesFile = File(target.rootProject.projectDir, "apikey.properties")
        val apikeyProperties = Properties()

        apikeyProperties.load(FileInputStream(apikeyPropertiesFile))

        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                defaultConfig.apply {
                    targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
                    buildConfigField("String", "BASE_URL", apikeyProperties.getProperty("BASE_URL"))
                    buildConfigField("String", "TOKEN", apikeyProperties.getProperty("TOKEN"))
                }
                buildFeatures.buildConfig = true
            }
            dependencies {
                add("implementation", libs.findBundle("paging").get())
                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))
            }
        }
    }
}