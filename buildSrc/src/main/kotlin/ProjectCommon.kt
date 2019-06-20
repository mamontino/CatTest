
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.ScriptHandlerScope
import org.gradle.kotlin.dsl.dependencies

private const val mavenBaseUrl = "http://maven.madg.ftc.ru/artifactory"

fun Project.mainRepositories() = applyMainRepositories(repositories)

fun ScriptHandlerScope.mainRepositories() = applyMainRepositories(repositories)

fun Project.applyCommonLibraries() {
	dependencies {
		implementation(Libraries.rxKotlin)
		implementation(Libraries.rxAndroid)

		implementation(Libraries.timber)

		implementation(Libraries.kotlinStdlib)
		implementation(Libraries.kotlinReflect)

		implementation(Libraries.dagger)
		kapt(Libraries.daggerCompiler)

		testImplementation(TestLibraries.kotlinMockito)
		testImplementation(TestLibraries.junit)
	}
}

private fun applyMainRepositories(handler: RepositoryHandler) = with(handler) {
	google()
	jcenter()
}

private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
	add("implementation", dependencyNotation)

private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
	add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
	add("testImplementation", dependencyNotation)
