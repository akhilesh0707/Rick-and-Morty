object RemoteDependencies {
    const val kotlin = Dependencies.KotlinDep.kotlin
    const val javax = Dependencies.JavaDep.javax
    val network = listOf(
        Dependencies.RetrofitDep.retrofit,
        Dependencies.RetrofitDep.moshiConverter,
        Dependencies.RetrofitDep.loggingInterceptor
    )
    const val coroutineCore = Dependencies.CoroutinesDep.coroutineCore
}