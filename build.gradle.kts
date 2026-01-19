group = "fr.ateastudio.farmersdelight"
version = "1.2.1"

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.nova)
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.xenondevs.xyz/releases")
}

dependencies {
    implementation(libs.nova)
}

addon {
    name = "FarmersDelight"
    version = project.version.toString()
    main = "fr.ateastudio.farmersdelight.NovaFarmersDelight"
    authors = listOf("Katalijst")
    description = "Farming's Delight ported to Nova"
    website = "https://atea-studio.fr/nova-addons"
    prefix = "FarmersDelight"
    
    // output directory for the generated addon jar is read from the "outDir" project property (-PoutDir="...")
    val outDir = project.findProperty("outDir")
    if (outDir is String)
        destination.set(File(outDir))
}
