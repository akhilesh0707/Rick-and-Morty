import modules.DataLibraries

plugins {
    id(Config.Plugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    //Modules
    implementation(project(Modules.domain))
    // Kotlin and Coroutines
    implementation(DataLibraries.kotlin)
    implementation(DataLibraries.coroutineCore)
    // JavaX
    implementation(DataLibraries.javax)
}