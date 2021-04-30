import modules.DomainLibraries

plugins {
    id(Config.Plugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(DomainLibraries.kotlin)
    implementation(DomainLibraries.coroutineCore)
    // JavaX
    implementation(DomainLibraries.javax)
}