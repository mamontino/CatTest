import Application.versionCode
import Application.versionName
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper

apply {
    plugin<AppPlugin>()
    plugin<KotlinAndroidPluginWrapper>()
}

android {
    compileSdkVersion(Application.maxApi)
    buildToolsVersion(Application.tools)

    defaultConfig {
        applicationId = Application.id
        multiDexEnabled = true
        minSdkVersion(Application.minApi)
        targetSdkVersion(Application.maxApi)
        versionCode = Application.versionCode
        versionName = Application.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments(mapOf("clearPackageData" to "true"))
        testBuildType = "debug"
    }

    splits {
        abi {
            isEnable = System.getenv("enable_apk_split") == "true"
            reset()
            include("armeabi-v7a")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField("String", "DATABASE_NAME", "\"cats_db\"")
            buildConfigField("String", "CAT_URL", "\"https://api.thecatapi.com/v1/images/\"")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            buildConfigField("boolean", "STETHO_ENABLED", "true")
            buildConfigField("String", "DATABASE_NAME", "\"cats_db\"")
            buildConfigField("String", "CAT_URL", "\"https://api.thecatapi.com/v1/images/\"")
        }
    }

    sourceSets {
        getByName("main").res.srcDirs(arrayOf("src/main/res"))
    }

    dexOptions {
        javaMaxHeapSize = "2g"
        keepRuntimeAnnotatedClasses = false
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Libraries.okhttpUrlconnection)
    implementation(Libraries.lifecycle)

    implementation(SupportLibraries.supportAnnotations)
    implementation(SupportLibraries.browser)

    implementation(SupportLibraries.appCompat)
    implementation(SupportLibraries.material)
    implementation(SupportLibraries.constraint)

    implementation(SupportLibraries.core)
    implementation(SupportLibraries.coreUI)
    implementation(SupportLibraries.cardView)

    implementation(Libraries.glide)
    kapt(Libraries.glideCompiler)

//    implementation 'androidx.vectordrawable:vectordrawable:1.0.1'

    implementation(Libraries.roomRuntime)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitConverterGson)
    implementation(Libraries.retrofitAdapterRxJava)
    implementation(Libraries.okhttpLogging)

    implementation(SupportLibraries.multidex)

    implementation(Libraries.moxy)
    implementation(Libraries.moxyAppCompat)
    kapt(Libraries.moxyCompiller)

    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroidSupport)
    kapt(Libraries.daggerAndroidProcessor)

    implementation(Libraries.gson)

    // Stetho
    implementation(Libraries.stetho)
    implementation(Libraries.stethoOkHttp)

    //	Test dependencies
    testImplementation(TestLibraries.kotlinMockito)
    testImplementation(TestLibraries.junit)

    androidTestImplementation(TestLibraries.kotlinMockito)
    androidTestImplementation(TestLibraries.mockitoAndroid)
    androidTestImplementation(TestLibraries.junitExt)
}
