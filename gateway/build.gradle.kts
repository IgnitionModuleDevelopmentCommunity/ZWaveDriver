plugins {
    java
    id("com.github.ben-manes.versions") version ("0.18.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly("com.inductiveautomation.ignitionsdk:gateway-api:8.1.1")
    toModl("commons-beanutils:commons-beanutils:1.9.4")
    toModl("com.fazecast:jSerialComm:2.6.2")
}
