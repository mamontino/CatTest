import org.gradle.kotlin.dsl.`kotlin-dsl`
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.repositories

plugins {
	`kotlin-dsl`
}

repositories {
	google()
	jcenter()
}

dependencies {
	implementation(gradleApi())
	implementation("com.android.tools.build:gradle:3.4.1")
}