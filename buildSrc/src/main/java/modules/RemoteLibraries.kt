package modules

object RemoteLibraries {
    const val kotlin = Libraries.KotlinDep.kotlin
    const val javax = Libraries.JavaDep.javax
    val retrofit = listOf(
        Libraries.RetrofitDep.retrofit,
        Libraries.RetrofitDep.moshiConverter,
        Libraries.RetrofitDep.loggingInterceptor
    )
    const val coroutineCore = Libraries.CoroutinesDep.coroutineCore
}