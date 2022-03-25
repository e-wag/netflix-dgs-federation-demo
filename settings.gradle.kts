pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        gradlePluginPortal()
    }
}
rootProject.name = "netflix-dgs-federation-demo"
include("accounts")
include("products")
include("reviews")
include("inventory")
include("apollo-gateway")
