import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("org.jetbrains.kotlin.jvm") version "2.3.0"
    id("org.jetbrains.intellij.platform") version "2.6.0"
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
// androidStudio("2024.2.1.8")
// bundledPlugin("org.jetbrains.android")
// intellijIdeaCommunity("2024.2.1")
// plugins("org.jetbrains.android:242.21829.142")
dependencies {
    intellijPlatform {
        androidStudio("2025.3.1.1")
        bundledPlugin("org.jetbrains.android")
        bundledPlugin("org.jetbrains.kotlin")
        bundledPlugin("com.intellij.java")
    }
    testImplementation("junit:junit:4.13.2")
}

intellijPlatform {
    version = "3.6.5.242"
    group = "com.murphy.proguard"
    pluginConfiguration {
        ideaVersion.sinceBuild.set("242")
        ideaVersion.untilBuild.set("253.*")
    }
}

// Set the JVM compatibility versions
kotlin {
    jvmToolchain(21)
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks {
    publishPlugin {
        // AndProguard token
        val file = rootProject.file("token.properties")
        if (file.exists()) {
            val localProperties = loadProperties(file.path)
            val tokenValue = localProperties["token"].toString()
            token.set(tokenValue)
        }
        channels.set(listOf("Stable"))
    }
}
