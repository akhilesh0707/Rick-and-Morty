package modules

import Versions

object Libraries {

    object KotlinDep {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    }

    object JavaDep {
        const val javax = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
    }

    object CoreDep {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val constraint =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navFragmentKtxVersion}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.navUiKtxVersion}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtxVersion}"
    }

    object LifeCycleDep {
        const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodelKtxVersion}"
        const val liveDataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataKtxVersion}"
        const val lifeCycleExtension =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleExtVersion}"
        const val lifeCycleRuntime =
            "androidx.lifecycle:lifecycle-runtime:${Versions.lifeCycleRuntimeVersion}"
        const val lifeCycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleRuntimeKtxVersion}"
    }

    object DaggerHiltDep {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroidVersion}"
        const val hiltAndroidKapt =
            "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidVersion}"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltVersion}"
        const val hiltKapt = "androidx.hilt:hilt-compiler:${Versions.hiltVersion}"
    }

    object CoroutinesDep {
        const val coroutineCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineCoreVersion}"
        const val coroutineAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineAndroidVersion}"
    }

    object GlideDep {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    }

    object TimberDep {
        const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    }

    object RetrofitDep {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val moshiConverter =
            "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverterVersion}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptorVersion}"
    }

    object RoomDep {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompilerKapt = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    }

    object LottieDep {
        const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
    }
}
