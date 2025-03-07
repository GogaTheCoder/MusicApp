// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("androidx.navigation.safeargs.kotlin") version "2.8.7" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (libs.androidx.gradle.plugin) // Версия Gradle Plugin
        classpath (libs.kotlin.gradle.plugin)
        classpath (libs.navigation.safe.args) // Для Navigation Component
    }
}