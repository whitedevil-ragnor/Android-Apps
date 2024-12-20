// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id ("com.google.dagger.hilt.android") version "2.52" apply false
}

//buildscript {
//    dependencies {
//        // Hilt plugin
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.53") // Match the version used above
//    }
//}
