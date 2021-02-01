// this file configures settings for the gradle build tools, as well as the project structure.
// Generally this doesn't need to be altered unless you are adding/removing sub-projects.

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
        mavenCentral()
        jcenter()
        maven {
            name = "snapshots"
            url = uri("http://nexus.inductiveautomation.com/repository/inductiveautomation-snapshots/")
        }

        maven {
            name = "thirdparty"
            url = uri("http://nexus.inductiveautomation.com/repository/inductiveautomation-thirdparty/")
        }

        maven {
            name = "releases"
            url = uri("http://nexus.inductiveautomation.com/repository/inductiveautomation-releases/")
        }
    }

    // set to use the local module build when resolving the plugin (for ease of development)
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "ignition-module-plugin" ->
                    useModule("com.inductiveautomation.gradle:ignition-module-plugin:${requested.version}")
            }
        }
    }
}

rootProject.name = "zwave-driver"

// link up our subprojects as part of this multi-project build.  Add/remove subprojects gradle path notation.
include(":gateway")

