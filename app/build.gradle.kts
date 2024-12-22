plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.kurs"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kurs"
        minSdk = 26
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

    // UI компоненты
    implementation("androidx.appcompat:appcompat:1.6.1") // Базовая библиотека для поддержки ActionBar и других UI элементов
    implementation("com.google.android.material:material:1.9.0") // Для компонентов Material Design (например, Button, TextField)
    implementation("androidx.activity:activity-ktx:1.7.2") // Для работы с Activity в Kotlin (если используется Kotlin)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // Для ConstraintLayout (который нужен для сложных макетов)


    // Room для работы с базой данных
    implementation("androidx.room:room-runtime:2.6.0") // Основная зависимость для Room
    annotationProcessor("androidx.room:room-compiler:2.6.0") // Для обработки аннотаций в Room

    // Жизненный цикл и ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0") // Для LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0") // Для ViewModel

    // Для тестов
    testImplementation("junit:junit:4.13.2") // Для юнит-тестов
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // Для тестов в Android
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0") // Для UI тестов

    // Дополнительные зависимости
    implementation("com.squareup.picasso:picasso:2.71828") // Для работы с изображениями (если нужны изображения в профиле)
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Для работы с API (если нужно)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Для конвертации данных из JSON

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    annotationProcessor(libs.room.compiler)
}