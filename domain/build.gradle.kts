plugins {
    id("movie.android.library")
    id("movie.android.hilt")
}

android {
    namespace = "com.blank.movie.domain"

}

dependencies {

    implementation(libs.kotlinx.coroutines.android)
}