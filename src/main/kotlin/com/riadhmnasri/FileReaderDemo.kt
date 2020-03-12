package com.riadhmnasri

import org.springframework.core.io.ClassPathResource

//private fun displayFileContent(fileName: String) = ClassPathResource(fileName).inputStream.use { it.bufferedReader().readText() }

private fun displayFileContent(fileName: String) = FileReaderDemo::class.java.getResource(fileName).readText()

fun main() {
    println(displayFileContent("/data/content.txt"))
}