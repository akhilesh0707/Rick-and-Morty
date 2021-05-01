package modules

object DomainLibraries {
    const val kotlin = Libraries.KotlinDep.kotlin
    const val javax = Libraries.JavaDep.javax
    const val coroutineCore = Libraries.CoroutinesDep.coroutineCore

    object Test {
        const val junit = Libraries.TestDep.junit
        const val coroutines = Libraries.TestDep.coroutines
        const val mockitoInline = Libraries.TestDep.mockitoInline
    }
}