import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.testing.Test
import org.gradle.language.jvm.tasks.ProcessResources

plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

val sourceSets = extensions.getByName("sourceSets") as SourceSetContainer
val mainSourceSet = sourceSets.getByName("main")

val integrationTestSourceSet = sourceSets.create("integrationTest") {
    java.srcDir("src/integrationTest/java")
    resources.srcDir("src/integrationTest/resources")

    compileClasspath += mainSourceSet.output
    runtimeClasspath += mainSourceSet.output
}

configurations.named("integrationTestImplementation") {
    extendsFrom(configurations.getByName("testImplementation"))
}

configurations.named("integrationTestRuntimeOnly") {
    extendsFrom(configurations.getByName("testRuntimeOnly"))
}

dependencies {
    add(
        "integrationTestImplementation",
        project(":patient-management-service")
    )

    // Spring Boot
    add(
        "integrationTestImplementation",
        "org.springframework.boot:spring-boot-starter-test"
    )

    add(
        "integrationTestImplementation",
        "org.springframework.boot:spring-boot-starter-jdbc"
    )

    // Testcontainers
    add(
        "integrationTestImplementation",
        "org.testcontainers:testcontainers-junit-jupiter:2.0.5"
    )

    add(
        "integrationTestImplementation",
        "org.testcontainers:testcontainers-postgresql:2.0.5"
    )

    // PostgreSQL JDBC driver
    add(
        "integrationTestRuntimeOnly",
        "org.postgresql:postgresql"
    )

    // REST API integration tests
    add(
        "integrationTestImplementation",
        "io.rest-assured:rest-assured:5.5.5"
    )
}

tasks.register<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"

    testClassesDirs = integrationTestSourceSet.output.classesDirs
    classpath = integrationTestSourceSet.runtimeClasspath

    useJUnitPlatform()

    shouldRunAfter(tasks.named<Test>("test"))
}

tasks.named("check") {
    dependsOn(tasks.named("integrationTest"))
}

tasks.bootJar {
    enabled = false
}

tasks.named<ProcessResources>("processIntegrationTestResources") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}