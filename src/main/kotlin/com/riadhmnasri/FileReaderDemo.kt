package com.riadhmnasri

import org.springframework.core.io.ClassPathResource

private fun displayFileContent(fileName: String) = ClassPathResource(fileName).inputStream.use { it.bufferedReader().readText() }

fun main() {
    println(displayFileContent("/data/content.txt"))
}