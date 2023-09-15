plugins {
    id("movie.android.feature")
}
android {
    namespace = "com.blank.movie.home"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
}