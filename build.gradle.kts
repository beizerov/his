plugins {
    id("org.springframework.boot") version "4.1.0" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    id("base")
}

allprojects {
    group = "com.beizerov.his"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    plugins.withId("java") {
        dependencies.add(
            "compileOnly",
            "org.projectlombok:lombok:1.18.38"
        )

        dependencies.add(
            "annotationProcessor",
            "org.projectlombok:lombok:1.18.38"
        )
    }

    plugins.withId("java-library") {
        dependencies.add(
            "compileOnly",
            "org.projectlombok:lombok:1.18.38"
        )

        dependencies.add(
            "annotationProcessor",
            "org.projectlombok:lombok:1.18.38"
        )
    }

    afterEvaluate {
        configurations.configureEach {
            resolutionStrategy.eachDependency {
                if (requested.group == "org.apache.commons" &&
                    requested.name == "commons-lang3") {

                    useVersion("3.18.0")

                    because(
                        "Fix CVE-2025-48924 - Uncontrolled recursion vulnerability"
                    )
                }
            }
        }
    }
}

tasks.named("check") {
    dependsOn(":integration-tests:integrationTest")
}