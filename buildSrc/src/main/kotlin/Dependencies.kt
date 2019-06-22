object Application {
	val id = "com.mamontov.cattest"
	val minApi = 16
	val maxApi = 28
	val tools = "28.0.3"
	val versionCode = 1
	val versionName = "1.0.0"
}

object Versions {
	val gradle = "3.3.2"
	val kotlin = "1.3.30"

	// Android
	val appCompatVersion = "1.0.2"
	val recyclerViewVersion = "1.0.0"
	val legacySupportVersion = "1.0.0"
	val materialVersion = "1.1.0-alpha05"
	val coreUIVersion = "1.0.0"
	val cardViewVersion = "1.0.0"
	val annotationVersion = "1.0.0"
	val browserVersion = "1.0.0"
	val coreVersion = "1.0.1"
	val multidex = "2.0.1"

	// Arch
	val roomVersion = "2.1.0"
	val lifecycleVersion = "2.0.0"

	// Rx
	val rxKotlin = "2.1.0"
	val rxAndroid = "2.0.1"

	// Dagger
	val dagger = "2.17"

	// Presentation
	val moxy = "1.5.3"

	// Networking
	val retrofit2 = "2.3.0"
	val okHttp = "3.11.0"
	val gson = "2.8.2"

	// Logging
	val timber = "4.6.1"

	// Testing
	val androidMockito = "2.18.3"
	val kotlinMockito = "1.6.0"
	val junit = "4.12"
	val junitExtVersion = "1.1.0"
	val testRunner = "1.1.1"

	val dataBinding = "3.3.2"
	val glide = "4.9.0"
	val constraintLayout = "1.1.3"

	// Other
	val stetho = "1.5.1"
}

object Classpaths {
	val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
	val kotlinAndroidExtensions = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
}

object Libraries {
	val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
	val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

	// Rx
	val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
	val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

	// Dagger
	val dagger = "com.google.dagger:dagger:${Versions.dagger}"
	val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
	val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
	val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
	val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

	// Moxy
	val moxy = "com.arello-mobile:moxy:${Versions.moxy}"
	val moxyAppCompat = "com.arello-mobile:moxy-app-compat:${Versions.moxy}"
	val moxyCompiller = "com.arello-mobile:moxy-compiler:${Versions.moxy}"

	// Retrofit
	val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
	val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
	val retrofitAdapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit2}"

	// OkHttp
	val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
	val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
	val okhttpUrlconnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okHttp}"

	val timber = "com.jakewharton.timber:timber:${Versions.timber}"
	val gson = "com.google.code.gson:gson:${Versions.gson}"

	val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
	val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

	// Arch
	val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
	val roomRx = "androidx.room:room-rxjava2:${Versions.roomVersion}"
	val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
	val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"

	// Stetho
	val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
	val stethoOkHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
}

object SupportLibraries {
	val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
	val material = "com.google.android.material:material:${Versions.materialVersion}"
	val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
	val coreUI = "androidx.legacy:legacy-support-core-ui:${Versions.coreUIVersion}"
	val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"
	val supportAnnotations = "androidx.annotation:annotation:${Versions.annotationVersion}"
	val browser = "androidx.browser:browser:${Versions.browserVersion}"
	val core = "androidx.core:core:${Versions.coreVersion}"
	val multidex = "androidx.multidex:multidex:${Versions.multidex}"
}

object TestLibraries {
	val mockitoAndroid = "org.mockito:mockito-android:${Versions.androidMockito}"
	val mockitoInline = "org.mockito:mockito-inline:${Versions.androidMockito}"
	val junit = "junit:junit:${Versions.junit}"
	val junitExt = "androidx.test.ext:junit:${Versions.junitExtVersion}"
	val kotlinMockito = "com.nhaarman:mockito-kotlin-kt1.1:${Versions.kotlinMockito}"
	val roomTesting = "androidx.room:room-testing:${Versions.roomVersion}"
}

