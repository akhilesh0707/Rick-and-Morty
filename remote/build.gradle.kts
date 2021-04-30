import modules.RemoteLibraries

plugins {
    id(Config.Plugins.kotlin)
    id(Config.Plugins.kotlinKapt)
}

dependencies {
    //Modules
    implementation(project(Modules.data))

    implementation(RemoteLibraries.kotlin)

    // Network (Retrofit, OkHttp, Interceptor, Moshi)
    RemoteLibraries.retrofit.forEach { implementation(it) }

    // Coroutines
    implementation(RemoteLibraries.coroutineCore)

    // JavaX
    implementation(RemoteLibraries.javax)
}