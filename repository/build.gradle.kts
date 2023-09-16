plugins {
    id("movie.android.library")
    id("movie.android.hilt")
}

android {
    namespace = "com.blank.movie.repository"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":data"))
    implementation(project(":domain"))
}