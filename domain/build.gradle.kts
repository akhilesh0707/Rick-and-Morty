import dependencies.DomainDep

plugins {
    id(Config.Plugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(DomainDep.kotlin)
    implementation(DomainDep.coroutineCore)
    // JavaX
    implementation(DomainDep.javax)
}