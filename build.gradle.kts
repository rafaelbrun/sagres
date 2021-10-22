import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.profusion"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}
tasks{
    shadowJar {
        manifest {
            attributes(Pair("Main-Class", "org.rafael.sagres.Sagres"))
        }
    }
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}