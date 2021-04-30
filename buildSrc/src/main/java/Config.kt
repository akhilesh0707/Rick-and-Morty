object Config {

    object Dependencies {
        const val androidGradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
        const val kotlinGradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val daggerHiltGradle =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroidVersion}"
        const val navigationSafArgsGradle =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.gradleNavigationArgVersion}"
    }

    object Plugins {
        const val kotlin = "kotlin"
        const val android = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
        const val navigationSafArgs = "androidx.navigation.safeargs.kotlin"
        const val kotlinKapt = "kotlin-kapt"
        const val dagger = "dagger.hilt.android.plugin"
        const val androidLibrary = "com.android.library"
    }

    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}