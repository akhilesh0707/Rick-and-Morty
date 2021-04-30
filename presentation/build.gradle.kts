import modules.PresentationLibraries

plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
}

android {
    compileSdkVersion(Versions.androidCompileSdkVersion)
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
    implementation(project(Modules.domain))
    implementation(PresentationLibraries.kotlin)
    implementation(PresentationLibraries.coroutineCore)
    // Dagger-Hilt (used for @InjectViewModel)
    PresentationLibraries.daggerHilt.forEach {
        implementation(it)
    }
    PresentationLibraries.daggerHiltKapt.forEach {
        kapt(it)
    }
    // JavaX
    implementation(PresentationLibraries.javax)
    // LifeCycle
    PresentationLibraries.lifeCycle.forEach {
        implementation(it)
    }
}