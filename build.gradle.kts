import org.gradle.api.JavaVersion.VERSION_11
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.20"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    test {
        useJUnitPlatform()
    }
}
