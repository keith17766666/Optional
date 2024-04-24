plugins {
    `java-library`
    `maven-publish`
}

group = "dev.keith.optional"
version = "1.0-SNAPSHOT"

publishing {
    publications {
        create<MavenPublication>("myLibrary") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "myRepo"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

repositories {
    mavenCentral()
    google()
}

dependencies {

}

tasks.test {
    useJUnitPlatform()
}

