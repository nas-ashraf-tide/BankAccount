plugins {
    id("java")
}

group = "co.tide.businessservices.integrations"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.34")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-junit-jupiter:5.2.0")
    testImplementation("org.assertj:assertj-core:3.26.3")
}

tasks.test {
    useJUnitPlatform()
}