plugins {
    id("movie.android.library")
    id("movie.android.hilt")
    id("movie.android.library.jacoco")
}

android {
    namespace = "com.blank.movie.domain"

}

dependencies {

    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)
}