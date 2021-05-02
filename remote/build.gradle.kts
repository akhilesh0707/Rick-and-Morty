import dependencies.RemoteDep

plugins {
    id(Config.Plugins.kotlin)
    id(Config.Plugins.kotlinKapt)
}

dependencies {
    //Modules
    implementation(project(Modules.data))

    implementation(RemoteDep.kotlin)

    // Network (Retrofit, OkHttp, Interceptor, Moshi)
    RemoteDep.retrofit.forEach { implementation(it) }

    // Coroutines
    implementation(RemoteDep.coroutineCore)

    // JavaX
    implementation(RemoteDep.javax)
}