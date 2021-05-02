package dependencies

object CacheDep {
    const val kotlin = Dependencies.KotlinDep.kotlin
    const val javax = Dependencies.JavaDep.javax
    val room = listOf(Dependencies.RoomDep.roomRuntime, Dependencies.RoomDep.roomKtx)
    const val roomKapt = Dependencies.RoomDep.roomCompilerKapt
}