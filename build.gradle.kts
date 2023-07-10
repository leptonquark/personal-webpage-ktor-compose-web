import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.google.devtools.ksp")
    application
}

group = "me.justin"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }
    js {
        moduleName = "justinsaler"
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    @OptIn(org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl::class)
    wasm {
        moduleName = "justinsaler"
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).copy(
                    static = (devServer?.static ?: mutableListOf()).apply {
                        add(project.rootDir.path)
                    },
                )
            }
        }
        binaries.executable()
    }
    sourceSets {
        val ktorVersion : String by project
        val kotlinInjectVersion : String by project

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material3)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-server-html-builder-jvm:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("me.tatarka.inject:kotlin-inject-runtime:$kotlinInjectVersion")
                kotlin.srcDir("build/generated/ksp/js/jsMain/kotlin")
            }
        }
        val wasmMain by getting
    }
}

dependencies{
    val kotlinInjectVersion : String by project
    add("kspJs", "me.tatarka.inject:kotlin-inject-compiler-ksp:$kotlinInjectVersion")
}

application {
    mainClass.set("me.justin.application.ServerKt")
}

compose.experimental {
    web.application {}
}

compose {
    val composeVersion: String by project
    val kotlinVersion: String by project

    kotlinCompilerPlugin.set(composeVersion)
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=$kotlinVersion")

}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}

configurations.all {
    resolutionStrategy.eachDependency {
        val kotlinVersion: String by project
        if (requested.module.name.startsWith("kotlin-stdlib")) {
            useVersion(kotlinVersion)
        }
    }
}