plugins {
    groovy
    id("org.openrewrite.build.recipe-library") version "latest.release"
}

group = "org.openrewrite.tools"
description = "Fork of object differ with the logging removed"

dependencies {
    testImplementation("org.spockframework:spock-core:1.2-groovy-2.5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:latest.release")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:latest.release")
}

tasks.test {
    dependsOn(tasks.jar)
}

// This fork publishes to the Code Genome Project only. The Sonatype repository and staging tasks come
// from the recipe-library convention plugin; disabling them leaves `publish` targeting just the `cgp`
// repository, and lets the shared publish workflow keep invoking closeAndReleaseSonatypeStagingRepository.
tasks.matching { it.name.contains("Sonatype") || it.name.contains("StagingRepositories") }
    .configureEach { enabled = false }
