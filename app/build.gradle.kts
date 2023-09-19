import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    alias(libs.plugins.ksp)
    alias(libs.plugins.detekt)
    application
}

group = "me.leptonquark"
version = "1.0"


kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }
    js {
        moduleName = "CV"
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
        moduleName = "CV"
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

    sourceSets.forEach {
        it.dependencies {
            val ktorVersion: String by project
            implementation(project.dependencies.enforcedPlatform("io.ktor:ktor-bom:$ktorVersion"))
        }
    }

    sourceSets {
        val kotlinInjectVersion: String by project

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1-wasm0")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty")
                implementation("io.ktor:ktor-server-html-builder-jvm")
                implementation("io.ktor:ktor-server-content-negotiation")
                implementation("io.ktor:ktor-serialization-kotlinx-json")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.9.1")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.624")
            }
        }

        val jsWasmMain by creating {
            dependsOn(commonMain)
        }

        val jsMain by getting {
            dependsOn(jsWasmMain)
            dependencies {
                implementation("io.ktor:ktor-client-core")
                implementation("io.ktor:ktor-client-js")
                implementation("io.ktor:ktor-client-content-negotiation")
                implementation("io.ktor:ktor-serialization-kotlinx-json")
                implementation("me.tatarka.inject:kotlin-inject-runtime:$kotlinInjectVersion")
                kotlin.srcDir("build/generated/ksp/js/jsMain/kotlin")
            }
        }
        val wasmMain by getting {
            dependsOn(jsWasmMain)
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core-wasm:1.5.1-wasm0")

            }
        }
    }
}

dependencies {
    val kotlinInjectVersion: String by project
    add("kspJs", "me.tatarka.inject:kotlin-inject-compiler-ksp:$kotlinInjectVersion")
}

application {
    mainClass.set("me.leptonquark.application.ServerKt")
}

compose.experimental {
    web.application {}
}

compose {
    val composeVersion: String by project

    kotlinCompilerPlugin.set(composeVersion)
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

detekt {
    config.setFrom("detekt-config.yml")
    source.setFrom(
        "src/commonMain/kotlin",
        "src/jsWasmMain/kotlin",
        "src/jsMain/kotlin",
        "src/jvmMain/kotlin",
        "src/wasmMain/kotlin"
    )
}
