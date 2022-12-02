pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ( "https://jitpack.io" )
    }
}
// 찾은 모듈을 저장하기 위한 HashMap 설정
val modules = hashMapOf<String, String>()

// rootProject의 projectDir을 찾고, findSubProjects를 동작한다.
rootProject.projectDir.listFiles()
    ?.forEach {
        findSubProjects(it)
    }

// 하위 디렉토리 구조를 찾기 위함
fun findSubProjects(file: File) {
    if (file.name.startsWith(".")) {
        return
    }

    // build.gradle.kts와 build.gradle을 찾기 위함
    if (file.name == "build.gradle.kts" || file.name == "build.gradle") {
        modules[file.parentFile.name] = file.parentFile.path
        return
    }

    // 재기 처리
    if (file.isDirectory) {
        file.listFiles()
            ?.forEach {
                findSubProjects(it)
            }
    }
}

// 모든 폴더 구조를 projectDir에 포함시켜 하위 폴더 모두를 포함시켜준다.
for (project in rootProject.children) {
    if (modules.containsKey(project.name)) {
        val directory = modules[project.name] ?: continue
        project.projectDir = File(directory)
    }
}
rootProject.name = "rejord"
include (":app")
include(":core")
include(":core:data")
include(":core:designsystem")
include(":core:domain")
include(":core:network")
include(":common")
include(":feature")
include(":feature:join")
include(":feature:login")
