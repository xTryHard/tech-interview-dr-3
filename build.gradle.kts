import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
}

group = "com.cardlay.techinterview"
version = "0.0.1-SNAPSHOT"

val targetJavaVersion = "16"
val testcontainersVersion = "1.15.1"

repositories {
    mavenCentral()
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.ALL
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
        vendor.set(JvmVendorSpec.ADOPTOPENJDK)
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-mail")

    implementation("org.liquibase:liquibase-core:4.2.2")
    runtimeOnly("org.postgresql:postgresql:42.2.19")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainersVersion")
    testImplementation("org.testcontainers:postgresql:$testcontainersVersion")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:$testcontainersVersion")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }

    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
        vendor.set(JvmVendorSpec.ADOPTOPENJDK)
    })
}
