import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.example" // TODO: Change this to your group
version = "1.0-SNAPSHOT" // TODO: Change this to your addon version

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.paperweight)
    alias(libs.plugins.nova)
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.xenondevs.xyz/releases")
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)
    implementation(libs.nova)
}

addon {
    id = project.name
    name = project.name.replaceFirstChar(Char::uppercase)
    version = project.version.toString()
    novaVersion = libs.versions.nova
    main = "com.example.ExampleAddon" // TODO: Change this to your main class
    authors = listOf("ExampleAuthor") // TODO: Set your list of authors
}

tasks {
    register<Copy>("addonJar") {
        group = "build"
        dependsOn("jar")
        from(File(project.layout.buildDirectory.get().asFile, "libs/${project.name}-${project.version}.jar"))
        into((project.findProperty("outDir") as? String)?.let(::File) ?: project.layout.buildDirectory.get().asFile)
        rename { "${addonMetadata.get().addonName.get()}-${project.version}.jar" }
    }
    
    withType<KotlinCompile> {
        compilerOptions { 
            jvmTarget = JvmTarget.JVM_21
        }
    }
}

afterEvaluate {
    tasks.getByName<Jar>("jar") {
        archiveClassifier = ""
    }
}