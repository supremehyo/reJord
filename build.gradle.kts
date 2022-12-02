subprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        gradlePluginPortal()
    }

    afterEvaluate {
        project.apply("$rootDir/gradle/common.gradle")
    }
}



tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}