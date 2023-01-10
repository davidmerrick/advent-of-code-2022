import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.github.davidmerrick.aoc2022"

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/davidmerrick/advent-of-code-utils")
        credentials {
            username = System.getenv("GITHUB_ACTOR")
            password = System.getenv("GITHUB_TOKEN")
        }
    }
}

plugins {
    kotlin("jvm") version "1.7.21"
}

dependencies {

    implementation("com.google.guava:guava:31.1-jre")
    implementation("io.github.davidmerrick.aoc:advent-of-code-utils:3.0.0")
    implementation("com.ginsberg:cirkle:1.0.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    implementation("com.github.shiguruikai:combinatoricskt:1.6.0")

    // Test

    testImplementation("org.spekframework.spek2:spek-runner-junit5:2.0.18")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
    testImplementation("io.mockk:mockk:1.13.2")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    test {
        useJUnitPlatform()
    }

    register("newDay") {
        group = "advent"
        description = """
            Creates folders and boilerplate for new AOC day.
            Pass in param "day" to initialize day by number (i.e. "-Pday=5") or else defaults to the day after the highest
            one in the source directory.
        """.trimIndent()

        doLast {
            val groupDirectory = project.group.toString().replace(".", "/")

            val dayKey = "day"
            // Compute the integer value for the next day
            val dayValue = if (project.properties.containsKey(dayKey)) {
                project.properties[dayKey]
            } else {
                File("${rootDir.path}/src/test/kotlin/$groupDirectory")
                    .list()
                    .filter { it.startsWith("day") }
                    .map { it.replace("day", "") }
                    .maxOf { it.toInt() }
                    .let { it + 1 }
            }

            println("Preparing day $dayValue \uD83C\uDF1E")

            val dayDirName = "day$dayValue"

            // Create directories
            listOf(
                "${rootDir.path}/src/test/kotlin/$groupDirectory/$dayDirName",
                "${rootDir.path}/src/test/resources/$groupDirectory/$dayDirName",
                "${rootDir.path}/src/main/kotlin/$groupDirectory/$dayDirName",
            ).forEach { mkdir(it) }

            // Create files
            File("${rootDir.path}/src/test/resources/$groupDirectory/$dayDirName/$dayDirName.txt").createNewFile()
            File("${rootDir.path}/src/test/resources/$groupDirectory/$dayDirName/example.txt").createNewFile()

            File("${rootDir.path}/src/test/kotlin/$groupDirectory/template/DayTest.kt")
                .copyTo(File("${rootDir.path}/src/test/kotlin/$groupDirectory/$dayDirName/Day${dayValue}Test.kt"))
        }
    }
}
