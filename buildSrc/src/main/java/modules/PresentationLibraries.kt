package modules

object PresentationLibraries {
    const val kotlin = Libraries.KotlinDep.kotlin
    const val javax = Libraries.JavaDep.javax
    const val coroutineCore = Libraries.CoroutinesDep.coroutineCore

    // Dagger-Hilt
    val daggerHilt = listOf(
        Libraries.DaggerHiltDep.hiltAndroid,
        Libraries.DaggerHiltDep.hiltViewModel
    )
    val daggerHiltKapt = listOf(
        Libraries.DaggerHiltDep.hiltAndroidKapt,
        Libraries.DaggerHiltDep.hiltKapt
    )
    val lifeCycle = listOf(
        Libraries.LifeCycleDep.viewModelKtx,
        Libraries.LifeCycleDep.liveDataKtx,
        Libraries.LifeCycleDep.lifeCycleExtension,
        Libraries.LifeCycleDep.lifeCycleRuntime,
        Libraries.LifeCycleDep.lifeCycleRuntimeKtx
    )
}