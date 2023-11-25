import org.jetbrains.compose.ComposeExtension

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
//    js(IR) {
//        moduleName = "floatingwindow"
//        browser {
//            commonWebpackConfig {
//                outputFileName = "floatingwindow.js"
//            }
//        }
//        binaries.executable()
//    }
    wasmJs {
        moduleName = "floatingwindow"
        browser()
        binaries.executable()
    }

    sourceSets {
//        val jsMain by getting {
//            dependencies {
//                implementation(compose.runtime)
//                implementation(compose.ui)
//                implementation(compose.foundation)
//                implementation(compose.material)
//                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
//                implementation(compose.components.resources)
//            }
//        }
        val wasmJsMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
    }
}

compose.experimental {
    web.application {}
}


allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/")
    }

    afterEvaluate {
        extensions.findByType(ComposeExtension::class.java)?.apply {
            val composeCompilerVersion = project.property("compose.compiler.version") as String
            kotlinCompilerPlugin.set(composeCompilerVersion)
            val kotlinVersion = project.property("kotlin.version") as String
            kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=$kotlinVersion")
        }
    }
}

