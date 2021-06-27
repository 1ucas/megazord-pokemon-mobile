plugins {
    id("com.android.application")
    kotlin("android")
}

repositories {
    maven(url="../../Megazord_Poke_Flutter/build/host/outputs/repo")
    maven(url="https://storage.googleapis.com/download.flutter.io")
}

dependencies {
    implementation(project(":Megazord_Poke_KMM"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("com.github.bumptech.glide:glide:4.11.0")
    implementation("br.com.manobray.megazord_poke_flutter.megazord_poke_flutter:flutter_debug:1.0")
}
android {

    compileSdkVersion(30)
    defaultConfig {
        applicationId = "br.com.manobray.megazordpokeappp.android"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        ndk.abiFilters.add("armeabi-v7a")
        ndk.abiFilters.add("arm64-v8a")
        ndk.abiFilters.add("x86_64")
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta07"
    }
}

dependencies {
    implementation(project(":Megazord_Poke_KMM"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("com.github.bumptech.glide:glide:4.11.0")
    implementation("androidx.activity:activity-compose:1.3.0-beta02")

    val composeVersion = "1.0.0-beta09"
    implementation("androidx.compose.ui:ui:${composeVersion}")
    implementation("androidx.compose.ui:ui-tooling:${composeVersion}")
    implementation("androidx.compose.foundation:foundation:${composeVersion}")
    implementation("androidx.compose.material:material:${composeVersion}")
    implementation("androidx.compose.material:material-icons-core:${composeVersion}")
    implementation("androidx.compose.material:material-icons-extended:${composeVersion}")
    implementation("androidx.compose.runtime:runtime-livedata:${composeVersion}")
}