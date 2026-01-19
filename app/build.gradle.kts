plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    /* Esta ruta es la que pegamos en el FireBase */
    namespace = "com.example.gestionincidenciascomunidad"

    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.gestionincidenciascomunidad"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(platform("com.google.firebase:firebase-bom:33.1.0")) // Gestiona versiones solo
    implementation("com.google.firebase:firebase-auth-ktx")     // Para el Login [cite: 21]
    implementation("com.google.firebase:firebase-database-ktx") // Para los datos [cite: 22]
    implementation("com.google.firebase:firebase-storage-ktx")  // Para las fotos [cite: 23]
}