plugins {
    groovy
    id("org.openrewrite.build.recipe-library") version "1.8.1"
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
