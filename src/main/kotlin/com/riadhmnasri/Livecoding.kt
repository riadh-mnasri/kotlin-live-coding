package com.riadhmnasri

fun main() {
    // Val vs Vars
    val text = "Hello Kotlin"
    //text = "test" // Val can not reassigned
    var text2 = "Hello Kotlin"

    val kotlinBook = Book("ISBNTEST1", "Programming Kotlin")
    val kotlinBook2 = Book("ISBNTEST1", "Programming Kotlin")
    //println(kotlinBook==kotlinBook2)

    // String templates
    //println("message: $text")
    val paragraph = """
        Hello
        Kotlin
        :-)
    """.trimIndent()
    //println(paragraph)
    //Exception
    //...
    // Null Safety
    // Elvis Operator
    val result = BookRepository().findBookByIsbn("ISBN") ?: Book("ISBNTEST3", "Domain Driven Design")
    println(result)

}

class BookRepository{
    private val books = listOf(
            Book("ISBNTEST1", "Programming Kotlin"),
            Book("ISBNTEST2", "Effective Kotlin")

    )
    fun findBookByIsbn(isbn: String): Book? = books.firstOrNull { it.isbn == isbn }
}
// class and data class
data class Book(val isbn: String, val title: String)
