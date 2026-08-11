import org.gradle.api.tasks.testing.Test

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

dependencies {
    implementation(project(":common"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // MyBatis
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:4.0.0")

    // DB & Liquibase
    implementation("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-liquibase")

    // Unit testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2:2.3.232")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}