plugins {
    id("movie.android.feature")
    alias(libs.plugins.org.jetbrains.kotlin.android)
}
android {
    namespace = "com.blank.movie.detailmovie"
    buildFeatures.dataBinding = true
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.youtube.player)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}