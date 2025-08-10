import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.SourceSetContainer

plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
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
    add("integrationTestImplementation", project(":patient-management-service"))

    add("integrationTestImplementation", "org.springframework.boot:spring-boot-starter-test")
    add("integrationTestImplementation", "org.springframework.boot:spring-boot-starter-jdbc")

    add("integrationTestImplementation", "org.testcontainers:junit-jupiter")
    add("integrationTestImplementation", "org.testcontainers:postgresql")

    add("integrationTestImplementation", "io.rest-assured:rest-assured:5.5.5")

    add("integrationTestImplementation", "com.h2database:h2:2.1.214")
}

tasks.register<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"

    testClassesDirs = integrationTestSourceSet.output.classesDirs
    classpath = integrationTestSourceSet.runtimeClasspath

    useJUnitPlatform()

    shouldRunAfter(tasks.named("test"))
}

tasks.named("check") {
    dependsOn(tasks.named("integrationTest"))
}

tasks.bootJar {
    enabled = false
}

tasks.named<Copy>("processIntegrationTestResources") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
