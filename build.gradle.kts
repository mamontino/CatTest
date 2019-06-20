import com.android.build.gradle.AppPlugin

plugins {
	base
}

buildscript {

	mainRepositories()

	dependencies {
		classpath(Classpaths.gradle)
		classpath(kotlin("gradle-plugin", version = Versions.kotlin))
		classpath(Classpaths.kotlinAndroidExtensions)
	}
}

allprojects {
	mainRepositories()
}

subprojects {

	if (project.name == "app") {
		apply(plugin = "com.android.application")
		apply<AppPlugin>()
	} else {
		apply(plugin = "com.android.library")
	}
	apply(plugin = "kotlin-android")
	apply(plugin = "kotlin-android-extensions")
	apply(plugin = "kotlin-kapt")

	applyCommonLibraries()
}
