import dependencies.DataDep

plugins {
    id(Config.Plugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    // Modules
    implementation(project(Modules.domain))
    // Kotlin
    implementation(DataDep.kotlin)
    // Coroutines
    implementation(DataDep.coroutineCore)
    // JavaX
    implementation(DataDep.javax)
    // Test Dependencies
    testImplementation(DataDep.Test.junit)
    testImplementation(DataDep.Test.assertJ)
    testImplementation(DataDep.Test.mockitoKotlin)
    testImplementation(DataDep.Test.mockitoInline)
    testImplementation(DataDep.Test.coroutines)
}
