android {
    compileSdkVersion(Application.maxApi)
    buildToolsVersion(Application.tools)

    defaultConfig {
        minSdkVersion(Application.minApi)
        targetSdkVersion(Application.maxApi)
        versionCode = Application.versionCode
        versionName = Application.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("androidTest").assets.srcDirs("$projectDir/schemas")
    }

    packagingOptions {
        pickFirst("protobuf.meta")
    }

}

dependencies {
    implementation(project(":domain"))

    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomRx)
    kapt(Libraries.roomCompiler)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitConverterGson)
    implementation(Libraries.retrofitAdapterRxJava)
    implementation(Libraries.okhttpLogging)
    implementation(Libraries.gson)

    implementation(SupportLibraries.supportAnnotations)

    androidTestImplementation(TestLibraries.junitExt)
    androidTestImplementation(TestLibraries.junit)

    androidTestImplementation(TestLibraries.mockitoAndroid)
    androidTestImplementation(TestLibraries.mockitoInline)
    androidTestImplementation(TestLibraries.roomTesting) {
        exclude(group = "androidx.annotation", module = "annotation")
    }
}
