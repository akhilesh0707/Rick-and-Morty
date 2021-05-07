import dependencies.RemoteDep

plugins {
    id(Config.Plugins.kotlin)
    id(Config.Plugins.kotlinKapt)
}

dependencies {
    // Modules
    implementation(project(Modules.data))
    // Kotlin
    implementation(RemoteDep.kotlin)
    // JavaX
    implementation(RemoteDep.javax)
    // Network (Retrofit, OkHttp, Interceptor, Moshi)
    RemoteDep.retrofit.forEach { implementation(it) }
    // Coroutines
    implementation(RemoteDep.coroutineCore)
    // Test
    // Test Dependencies
    testImplementation(RemoteDep.Test.junit)
    testImplementation(RemoteDep.Test.assertJ)
    testImplementation(RemoteDep.Test.mockitoKotlin)
    testImplementation(RemoteDep.Test.mockitoInline)
    testImplementation(RemoteDep.Test.coroutines)
}