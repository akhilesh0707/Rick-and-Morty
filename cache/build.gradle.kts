import modules.CacheLibraries

plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
}

android {
    compileSdkVersion(Versions.androidCompileSdkVersion)
    buildToolsVersion(Versions.androidBuildToolsVersion)

    defaultConfig {
        minSdkVersion(Versions.androidMinSdkVersion)
        targetSdkVersion(Versions.androidTargetSdkVersion)
        versionCode(Environments.Release.appVersionCode)
        versionName(Environments.Release.appVersionName)

        testInstrumentationRunner(Config.testRunner)
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    //Modules
    implementation(project(Modules.data))
    // Kotlin
    implementation(CacheLibraries.kotlin)
    // JavaX
    implementation(CacheLibraries.javax)
    // Room
    CacheLibraries.room.forEach {
        api(it)
    }
    kapt(CacheLibraries.roomKapt)
}