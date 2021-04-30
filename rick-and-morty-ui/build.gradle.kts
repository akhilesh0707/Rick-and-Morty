import modules.UiLibraries

plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.navigationSafArgs)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.dagger)
}

android {
    compileSdkVersion(Versions.androidCompileSdkVersion)
    buildToolsVersion(Versions.androidBuildToolsVersion)

    defaultConfig {
        applicationId(Environments.Release.appId)
        minSdkVersion(Versions.androidMinSdkVersion)
        targetSdkVersion(Versions.androidTargetSdkVersion)
        versionCode(Environments.Release.appVersionCode)
        versionName(Environments.Release.appVersionName)

        testInstrumentationRunner(Config.testRunner)

        // Configs
        buildConfigField("String", "BASE_URL", "\"" + Environments.Release.baseUrl + "\"")
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Modules
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.remote))
    implementation(project(Modules.cache))
    implementation(project(Modules.presentation))
    // Core Dependencies
    implementation(UiLibraries.kotlin)
    implementation(UiLibraries.coreKtx)
    implementation(UiLibraries.appCompat)
    implementation(UiLibraries.material)
    implementation(UiLibraries.constraint)
    implementation(UiLibraries.navigationFragmentKtx)
    implementation(UiLibraries.navigationUiKtx)
    implementation(UiLibraries.activityKtx)
    // LifeCycle
    UiLibraries.LifeCycle.forEach {
        implementation(it)
    }
    // Dagger-Hilt
    UiLibraries.DaggerHilt.forEach {
        implementation(it)
    }
    UiLibraries.DaggerHiltKapt.forEach {
        kapt(it)
    }
    // Coroutines
    UiLibraries.Coroutines.forEach {
        implementation(it)
    }
    // Glide
    implementation(UiLibraries.glide)
    kapt(UiLibraries.glideKapt)
    // Timber
    implementation(UiLibraries.timber)
    // Lottie animation
    implementation(UiLibraries.lottie)
}