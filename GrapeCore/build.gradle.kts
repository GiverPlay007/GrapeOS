plugins {
    id("java")
}

group = "me.giverplay.grape.core"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":GrapeSDK"))
}
