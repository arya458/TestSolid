object Dependencies {


    //    [libraries]
//    androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
    val androidx_core_ktx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }

    //    junit = { group = "junit", name = "junit", version.ref = "junit" }
    val junit by lazy { "junit:junit:${Versions.junit}" }

    //    androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
    val androidx_junit by lazy { "androidx.test.ext:junit:${Versions.junitVersion}" }

    //    androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
    val androidx_espresso_core by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoCore}" }

    //    androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
    val androidx_lifecycle_runtime_ktx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}" }

    //    androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
    val androidx_activity_compose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }

    //    androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
    val androidx_compose_bom by lazy { "androidx.compose:compose-bom:${Versions.composeBom}" }

    //    androidx-ui = { group = "androidx.compose.ui", name = "ui" }
    val androidx_ui by lazy { "androidx.compose.ui:ui" }

    //    androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
    val androidx_ui_graphics by lazy { "androidx.compose.ui:ui-graphics" }

    //    androidx-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
    val androidx_ui_tooling by lazy { "androidx.compose.ui:ui-tooling" }

    //    androidx-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
    val androidx_ui_tooling_preview by lazy { "androidx.compose.ui:ui-tooling-preview" }

    //    androidx-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
    val androidx_ui_test_manifest by lazy { "androidx.compose.ui:ui-test-manifest" }

    //    androidx-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
    val androidx_ui_test_junit4 by lazy { "androidx.compose.ui:ui-test-junit4" }

    //    androidx-material3 = { group = "androidx.compose.material3", name = "material3" }
    val androidx_material3 by lazy { "androidx.compose.material3:material3" }


    //    androidx-hilt-navigation-compose = { module = "androidx.hilt:hilt-navigation-compose", version.ref = "hiltNavigationCompose" }
    val androidx_hilt_navigation_compose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}" }

    //    hilt-android = { module = "com.google.dagger:hilt-android", version.ref = "hiltAndroid" }
    val hilt_android by lazy { "com.google.dagger:hilt-android:${Versions.hiltAndroid}" }

    //    hilt-android-compiler = { module = "com.google.dagger:hilt-android-compiler", version.ref = "hiltAndroid" }
    val hilt_android_compiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}" }

    // implementation "androidx.work:work-runtime-ktx:$work_version"
    val work_runtime_ktx by lazy { "androidx.work:work-runtime-ktx:${Versions.work_version}" }


    //    implementation("com.google.android.gms:play-services-auth:20.5.0")
    val play_services_auth by lazy { "com.google.android.gms:play-services-auth:${Versions.play_services_auth}" }

    //    implementation("com.google.android.gms:play-services-auth-api-phone:18.0.1")
    val play_services_auth_api_phone by lazy { "com.google.android.gms:play-services-auth-api-phone:${Versions.play_services_auth_api_phone}" }


}



