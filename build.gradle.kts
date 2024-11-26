import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    id("org.jetbrains.intellij.platform") version "2.1.0"
}

version = "3.6.3"
group = "com.murphy.proguard"

intellijPlatform {
    pluginConfiguration {
        ideaVersion.sinceBuild.set("242")
        ideaVersion.untilBuild.set(provider { null })
    }
}

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
// http://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/build_number_ranges.html
dependencies {
    intellijPlatform {
        //bundledPlugin("org.jetbrains.android")
        intellijIdeaCommunity("2024.2")
        bundledPlugin("com.intellij.java")
        bundledPlugin("org.jetbrains.kotlin")
        plugins("org.jetbrains.android:242.10180.25")
        instrumentationTools()
    }
}

// Set the JVM compatibility versions
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks {
    publishPlugin {
        // AndProguard token
        val file = rootProject.file("token.properties")
        val localProperties = loadProperties(file.path)
        val tokenValue = localProperties["token"].toString()
        token.set(tokenValue)
        channels.set(listOf("Stable"))
    }
}
