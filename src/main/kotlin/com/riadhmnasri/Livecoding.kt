package com.riadhmnasri

import com.riadhmnasri.domain.Book

fun main() {
    // Java vs Kotlin (By example)
    // Val vs Vars
    val text = "Hello Kotlin"
    var text2 = "Hello Java"
    text2 = "Hello Scala"
    println(text2)

    // Type Inference (from variable, from interfaces)
    val i = 2
    println(i.plus(3))

    // Class and data classes (equality) (classes instantiating (no more new))
    // Referential equality and structural equality
    val bookKotlin = Book("ISBNTEST1", "Hello Kotlin")
    val book2Kotlin = Book("ISBNTEST1", "Hello Kotlin")
    println(bookKotlin === book2Kotlin)


    // String templates
    println("Display $text")

    // String paragraph
    val json = """
        {
        "message": "Hello Sopra Steria"
        }
    """.trimIndent()
    println(json)

    // Default values (no need to overload)
    // See Book class constructor
    val book3Kotlin = Book("ISBNTEST1", "Hello Kotlin", 30.0)
    println(book3Kotlin)


    // Exception (all are unchecked exceptions)/annotation @Throws when kotlin function called from Java
    // All exceptions are unchecked

    // Null Safety/Declare Not Nullable types/Elvis operator
    // Null Safety
    val bookDDD = Book("ISBNTEST3", "DDD")
    val bookFound = BookRepository().findBookByIsbn("ISBNTESTT1")
    println(bookFound?.title)


    // Elvis Operator
    val bookFound2 = BookRepository().findBookByIsbn("ISBNTEST1") ?: bookDDD
    println(bookFound2)


    // Smart casts/explicit casts
    printAsStringInUppercase("text")



    // Import renaming // Rename class Book en BookDomain without changing it


    // Ranges/loops
    val ints = 1..10
    for (i in ints){
        println(i)
    }

    // When expression/Sealed class
    val resultSearch = BookRepository().findBookByIsbnV2("ISBNTEST1")
    println(processResult(resultSearch))



    // Inheritance (default final, open, override)
    println(Car().name())

    // Delegation
    val activity = Activity(Tennis())
    println(activity.name() +" - "+ activity.cost())


    // Named parameters
    val book4 = Book(title = "DDD", isbn="ISBNTEST3")
    println(book4)


    // Extensions functions
    println(bookKotlin.rate(5))

    // Standard library functions
    // Apply // returns the original instance


    // Let // Kind of map to an object


    // With // return the processed result


    // Run


    // Lazy


    // Use

    // Collections (Mutable, Immutable)


    // Mutable collections


    // Operator overloading: if we have time


    // Mini DSL: if we have time
    println("*****" shouldBeEqualTo bookKotlin.rate(5))


}

infix fun <T> T.shouldBeEqualTo(expected: T?): Boolean = this == expected

fun Book.rate(note: Int): String{
    return when(note){
        note -> "*".repeat(note)
        else -> "Invalid note"
    }
}

fun processResult(bookResult: BookResult): String {
    return when (bookResult) {
        is BookResult.BookFound -> "Cool !"
        is BookResult.BookNotFound -> "Sorry, may be next time !"
    }
}

fun printAsStringInUppercase(obj: Any) {
    if (obj is String) {
        println(obj.toUpperCase())
    }
}

class BookRepository {
    private val books = listOf(
        Book("ISBNTEST1", "Programming Kotlin"),
        Book("ISBNTEST2", "Effective Kotlin")

    )

    fun findBookByIsbn(isbn: String): Book? = books.firstOrNull { it.isbn == isbn }

    fun findBookByIsbnV2(isbn: String): BookResult =
        when (val result = books.firstOrNull { it.isbn == isbn }) {
            null -> BookResult.BookNotFound(isbn)
            else -> BookResult.BookFound(result)
        }
}
