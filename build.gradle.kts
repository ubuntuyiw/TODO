buildscript {
    dependencies {
        classpath (libs.androidx.navigation.safe.args.gradle.plugin)

        val kotlinVersion = "1.8.10"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
        classpath (libs.kotlin.serialization)


    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false

    id("com.google.dagger.hilt.android") version "2.45" apply false
    kotlin("kapt") version "1.8.10" apply false
    id("com.android.library") version "8.0.0-rc01" apply false

    id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}