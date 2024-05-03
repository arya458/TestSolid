plugins {
    id(Plugins.androidApplication)
    id(Plugins.jetbrainsKotlinAndroid)
    id(Plugins.hilt)
    id(Plugins.kapt)
}

android {
    namespace = "com.aria.dansh.testsolid"
    compileSdk = SDK.COMPILE

    defaultConfig {
        applicationId = "com.aria.dansh.testsolid"
        minSdk = SDK.MIN
        targetSdk = SDK.TARGET
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JDKVersion.JavaVer
        targetCompatibility = JDKVersion.JavaVer
    }
    kotlinOptions {
        jvmTarget = JDKVersion.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.androidx_core_ktx)
    implementation(Dependencies.androidx_lifecycle_runtime_ktx)
    implementation(Dependencies.androidx_activity_compose)
    implementation(platform(Dependencies.androidx_compose_bom))

    implementation(Dependencies.androidx_ui)
    implementation(Dependencies.androidx_ui_graphics)
    implementation(Dependencies.androidx_ui_tooling_preview)
    implementation(Dependencies.androidx_material3)
    testImplementation(Dependencies.junit)

    androidTestImplementation(Dependencies.androidx_junit)
    androidTestImplementation(Dependencies.androidx_espresso_core)
    androidTestImplementation(platform(Dependencies.androidx_compose_bom))
    androidTestImplementation(Dependencies.androidx_ui_test_junit4)
    debugImplementation(Dependencies.androidx_ui_tooling)
    debugImplementation(Dependencies.androidx_ui_test_manifest)

    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_android_compiler)
    implementation(Dependencies.androidx_hilt_navigation_compose)

    implementation(Dependencies.play_services_auth)
    implementation(Dependencies.play_services_auth_api_phone)

    implementation(Dependencies.work_runtime_ktx)

    // Arrow
    implementation("io.arrow-kt:arrow-core:1.2.0")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0")



}