package modules

object UiLibraries {

    // Kotlin
    const val kotlin = Libraries.KotlinDep.kotlin

    // Core
    const val coreKtx = Libraries.CoreDep.coreKtx
    const val appCompat = Libraries.CoreDep.appCompat
    const val material = Libraries.CoreDep.material
    const val constraint = Libraries.CoreDep.constraint
    const val navigationFragmentKtx = Libraries.CoreDep.navigationFragmentKtx
    const val navigationUiKtx = Libraries.CoreDep.navigationUiKtx
    const val activityKtx = Libraries.CoreDep.activityKtx

    // LifeCycle
    val LifeCycle = listOf(
        Libraries.LifeCycleDep.viewModelKtx,
        Libraries.LifeCycleDep.lifeCycleExtension,
        Libraries.LifeCycleDep.lifeCycleRuntime,
        Libraries.LifeCycleDep.lifeCycleRuntimeKtx
    )

    // Hilt
    val DaggerHilt = listOf(
        Libraries.DaggerHiltDep.hiltAndroid,
        Libraries.DaggerHiltDep.hiltViewModel
    )

    val DaggerHiltKapt = listOf(
        Libraries.DaggerHiltDep.hiltAndroidKapt,
        Libraries.DaggerHiltDep.hiltKapt
    )

    // Coroutines
    val Coroutines = listOf(
        Libraries.CoroutinesDep.coroutineCore,
        Libraries.CoroutinesDep.coroutineAndroid
    )

    const val glide = Libraries.GlideDep.glide
    const val glideKapt = Libraries.GlideDep.glideKapt
    const val timber = Libraries.TimberDep.timber
    const val lottie = Libraries.LottieDep.lottie
}