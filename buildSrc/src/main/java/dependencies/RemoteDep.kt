package dependencies

object RemoteDep {
    const val kotlin = Dependencies.KotlinDep.kotlin
    const val javax = Dependencies.JavaDep.javax
    val retrofit = listOf(
        Dependencies.RetrofitDep.retrofit,
        Dependencies.RetrofitDep.moshiConverter,
        Dependencies.RetrofitDep.loggingInterceptor
    )
    const val coroutineCore = Dependencies.CoroutinesDep.coroutineCore

    object Test {
        const val junit = Dependencies.TestDep.junit
        const val coroutines = Dependencies.TestDep.coroutinesTest
        const val mockitoKotlin = Dependencies.TestDep.mockitoKotlin
        const val mockitoInline = Dependencies.TestDep.mockitoInline
        const val assertJ = Dependencies.TestDep.assertJ
    }
}