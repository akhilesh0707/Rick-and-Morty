package modules

object CacheLibraries {
    const val kotlin = Libraries.KotlinDep.kotlin
    const val javax = Libraries.JavaDep.javax
    val room = listOf(Libraries.RoomDep.roomRuntime, Libraries.RoomDep.roomKtx)
    const val roomKapt = Libraries.RoomDep.roomCompilerKapt
}