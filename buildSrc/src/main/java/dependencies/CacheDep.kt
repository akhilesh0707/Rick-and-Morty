package dependencies

object CacheDep {
    const val kotlin = Dependencies.KotlinDep.kotlin
    const val javax = Dependencies.JavaDep.javax
    val room = listOf(Dependencies.RoomDep.roomRuntime, Dependencies.RoomDep.roomKtx)
    const val roomKapt = Dependencies.RoomDep.roomCompilerKapt

    object Test {
        const val junit = Dependencies.TestDep.junit
        const val coroutines = Dependencies.TestDep.coroutinesTest
        const val assertJ = Dependencies.TestDep.assertJ
        const val testCore = Dependencies.TestDep.testCore
        const val testExtJunit = Dependencies.TestDep.testExtJunit
        const val robolectric = Dependencies.TestDep.robolectric
        const val roomTest = Dependencies.TestDep.roomTest
    }
}