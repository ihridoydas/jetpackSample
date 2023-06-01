# jetpackSample
# Setting Up a Compose Multiplatform Project in Android Studio

Follow the steps below to set up a Compose Multiplatform project in Android Studio.

## Prerequisites
- Make sure you can create normal Kotlin Multiplatform Mobile (KMM) projects in Android Studio. If not, watch this [video tutorial](https://youtu.be/7Wl-G8aXxDA) to learn how to set it up.

## Steps

1. **Create a new KMM project**
    - Select 'Regular' for dependency management.

2. **Configure Gradle Properties**
    - Open `gradle.properties` and specify the necessary versions for Kotlin, AGP and Compose:

      ```properties
      kotlin.version=1.8.20
      agp.version=8.0.1
      compose.version=1.4.0
      ```

    - Paste these flags to enable Compose Multiplatform:

      ```properties
      org.jetbrains.compose.experimental.uikit.enabled=true
      kotlin.native.cacheKind=none
      ```

3. **Configure Project-Level Build Script**
    - Open `build.gradle.kts` (project) and replace the plugins in the plugins block with this:

      ```kotlin
      kotlin("multiplatform").apply(false)
      id("com.android.application").apply(false)
      id("com.android.library").apply(false)
      id("org.jetbrains.compose").apply(false)
      ```

4. **Configure Shared Module Build Script**
    - Open `build.gradle.kts` (shared) and do the following:
        - Add `id("org.jetbrains.compose")` to the plugins block.
        - Paste this below `baseName = "shared"`: `isStatic = true`
        - Add the Compose Multiplatform dependencies in the `sourceSets` block for the shared code:

          ```kotlin
          val commonMain by getting {
              dependencies {
                  implementation(compose.runtime)
                  implementation(compose.foundation)
                  implementation(compose.material)
                  @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                  implementation(compose.components.resources)
              }
          }
          ```

5. **Configure Android App Module Build Script**
    - Go to `build.gradle.kts` (androidApp) and add the Compose Multiplatform plugin in the plugins block: `id("org.jetbrains.compose")`

6. **Configure Settings Script**
    - Open `settings.gradle.kts` and do the following:
        - Add the Compose Multiplatform Maven path in both the repositories and `dependencyResolutionManagement` blocks: `maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")`
        - Paste this plugins block in the `pluginManagement` block:

          ```kotlin
          plugins {
              val kotlinVersion = extra["kotlin.version"] as String
              val agpVersion = extra["agp.version"] as String
              val composeVersion = extra["compose.version"] as String
   
              kotlin("jvm").version(kotlinVersion)
              kotlin("multiplatform").version(kotlinVersion)
              kotlin("android").version(kotlinVersion)
   
              id("com.android.application").version(agpVersion)
              id("com.android.library").version(agpVersion)
   
              id("org.jetbrains.compose").version(composeVersion)
          }
          ```

7. **Sync the Project**
    - If everything went well, you shouldn't have any errors. Now you can create Composables in your shared module and share them between Android and iOS.