plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
}


android {
    namespace = "nur.toza.pagingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "nur.toza.pagingapp"
        minSdk = 24
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
    buildFeatures{
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    //RxJava3 with retrofit
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    //Paging library
    val paging_version = "3.2.1"

    implementation("androidx.paging:paging-runtime:$paging_version")
    // optional - RxJava3 support
    implementation("androidx.paging:paging-rxjava3:$paging_version")

    //Hilt Dagger
    implementation ("com.google.dagger:hilt-android:2.51")
    annotationProcessor ("com.google.dagger:hilt-compiler:2.51")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // Skip this if you don't want to use integration libraries or configure Glide.
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.0-alpha04")
    implementation("androidx.lifecycle:lifecycle-livedata:2.5.0-alpha04")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
}