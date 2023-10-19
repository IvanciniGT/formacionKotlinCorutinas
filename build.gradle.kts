plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("kapt") version "1.9.10"
    application
}

group = "tech.iochannel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation( "com.google.dagger:dagger:2.48")
    kapt("com.google.dagger:dagger-compiler:2.48")


}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}