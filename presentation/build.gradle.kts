android {
    compileSdkVersion(Application.maxApi)
    buildToolsVersion(Application.tools)

    defaultConfig {
        minSdkVersion(Application.minApi)
        targetSdkVersion(Application.maxApi)
        versionCode = Application.versionCode
        versionName = Application.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

kapt {
    arguments {
        arg("moxyReflectorPackage", "com.mamontov.cattest.presentation")
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Libraries.moxy)
    kapt(Libraries.moxyCompiller)
}
