plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":Megazord_Poke_KMM"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("com.github.bumptech.glide:glide:4.11.0")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "br.com.manobray.megazordpokeappp.android"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}