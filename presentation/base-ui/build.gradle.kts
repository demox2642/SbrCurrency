plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.tsrttur.presentation.base-ui"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdkVersion

        testInstrumentationRunner = Config.androidTestInstrumentation
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerVersion = "1.7.10"
        kotlinCompilerExtensionVersion = Versions.compose
    }
    namespace = "com.example.base_ui"
}

dependencies {

    implementation(AndroidX.core_ktx)
    implementation(AndroidX.appcompat)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation(AndroidX.composeUiToolingPreview)
    debugImplementation(AndroidX.composeUiTooling)
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeMaterial)
    api(Accompanist.systemUiController)
    api(Accompanist.insets)
    api(Accompanist.flowLayout)
    implementation(AndroidX.composeMaterialIconsExtended)
    implementation("com.squareup.picasso:picasso:2.71828")
}
