buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    }
}
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