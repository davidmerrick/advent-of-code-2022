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
    kotlin("jvm") version "1.7.20"
}

dependencies {

    implementation("com.google.guava:guava:31.1-jre")
    implementation("io.github.davidmerrick.aoc:advent-of-code-utils:1.0.7")
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
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    test {
        useJUnitPlatform()
    }
}
