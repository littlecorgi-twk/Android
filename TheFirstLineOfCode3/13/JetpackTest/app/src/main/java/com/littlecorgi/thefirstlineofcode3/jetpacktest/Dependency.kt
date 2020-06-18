package com.littlecorgi.thefirstlineofcode3.jetpacktest

class Dependency {
    val libraries = ArrayList<String>()

    fun implementation(lib: String) {
        libraries.add(lib)
    }
}

fun dependencies(block: Dependency.() -> Unit): List<String> {
    val dependency = Dependency()
    dependency.block()
    return dependency.libraries
}

fun main() {
    val libraries = dependencies {
        implementation("com.github.bumptech.glide:glide:4.11.0")
    }
    for (lib in libraries) {
        println(lib)
    }
}