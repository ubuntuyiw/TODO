plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)

    id ("androidx.navigation.safeargs.kotlin")
    id ("kotlinx-serialization")

    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.ubuntuyouiwe.todo"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ubuntuyouiwe.todo"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    viewBinding {
        enable= true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)


    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt (libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.fragment)


    //Navigation
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)


    //Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Paging3
    implementation(libs.androidx.paging.runtime.ktx)


    //Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)



}