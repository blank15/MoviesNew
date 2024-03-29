plugins {
    id("movie.android.library")
    id("movie.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.blank.movie.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.bundles.network)
    implementation(libs.kotlinx.serialization.json)
}