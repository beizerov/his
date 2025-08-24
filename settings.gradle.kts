/*
 * HIS multi-module project settings.
 */

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "his"

include(
    "common",
    "patient-management-service",
    "integration-tests"
)
