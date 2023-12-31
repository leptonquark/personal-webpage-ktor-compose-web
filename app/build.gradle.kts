import org.jetbrains.kotlin.gradle.targets.js.ir.DefaultIncrementalSyncTask
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
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
    wasmJs {
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

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.bundles.compose)
                implementation(libs.serialization.json)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.bundles.ktor.server)
                implementation(libs.css)
                implementation(libs.html.jvm)
            }
        }

        val jsWasmMain by creating {
            dependsOn(commonMain)
        }

        val jsMain by getting {
            dependsOn(jsWasmMain)
            dependencies {
                implementation(libs.bundles.ktor.js)
                implementation(libs.inject.runtime)
                kotlin.srcDir("build/generated/ksp/js/jsMain/kotlin")
            }
        }
        val wasmJsMain by getting {
            dependsOn(jsWasmMain)
            dependencies {
                implementation(libs.serialization.wasm)
            }
        }
    }
}

dependencies {
    add("kspJs", libs.inject.compiler)
}

application {
    mainClass.set("me.leptonquark.application.ServerKt")
}

compose {
    experimental {
        web.application
    }
    kotlinCompilerPlugin.set(libs.versions.composecompiler.get())
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=${libs.versions.kotlin.get()}")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}

tasks.named<KotlinWebpack>("jsBrowserProductionWebpack") {
    dependsOn(tasks.named<DefaultIncrementalSyncTask>("wasmJsProductionExecutableCompileSync"))
}

tasks.named<DefaultIncrementalSyncTask>("jsProductionExecutableCompileSync") {
    dependsOn(tasks.named<Copy>("wasmJsBrowserProductionExecutableDistributeResources"))
}

tasks.named<DefaultIncrementalSyncTask>("jsProductionExecutableCompileSync") {
    dependsOn(tasks.named<KotlinWebpack>("wasmJsBrowserProductionWebpack"))
}

project.tasks.whenTaskAdded {
    if (name == "compileJsWasmMainKotlinMetadata") {
        enabled = false
    }
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.module.name.startsWith("kotlin-stdlib")) {
            useVersion(libs.versions.kotlin.get())
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
        "src/wasmJsMain/kotlin",
    )
}