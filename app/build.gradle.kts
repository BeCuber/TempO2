plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.belencubero.tempO2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.belencubero.tempO2"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Dependencia para las pruebas unitarias con JUnit
    testImplementation("junit:junit:4.13.2")

    // Dependencias para pruebas instrumentadas (opcional si las usas)
    androidTestImplementation(libs.junit.v113)
    androidTestImplementation(libs.espresso.core.v361)
}