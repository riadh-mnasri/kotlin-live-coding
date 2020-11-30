package com.riadhmnasri

import com.riadhmnasri.domain.Book as BookDomain

fun main() {
    // Java vs Kotlin (By example
    // Val vs Vars
    val text = "Hello Kotlin"
    var text2 = "Hello Java"
    text2 = "Hello Scala"
    println(text2)

    // Type Inference (from variable, from interfaces)
    val i = 2
    println(i.plus(5))

    // Class and data classes (equality) (classes instantiating (no more new))
    // Referential equality and structural equality
    val bookKotlin = BookDomain("ISBNTEST1", "Programming Kotlin", 0.0)
    val anotherBook = BookDomain("ISBNTEST1", "Programming Kotlin", 0.0)
    println(bookKotlin === anotherBook)

    // String templates
    println("display $text")
    val paragraph = """
        Hello
        Sopra Steria
        :-)
    """.trimIndent()
    println(paragraph)

    // Default values (no need to overload)
    // See Book class constructor
    println(BookDomain("ISBNTEST", "Hello Kotlin"))

    // Exception (all are unchecked exceptions)/annotation @Throws when kotlin function called from Java
    // All exceptions are unchecked

    // Null Safety/Declare Not Nullable types/Elvis operator
    // Null Safety
    val foundBook = BookRepository().findBookByIsbn("ISBNTEST11")
    println(foundBook?.title)
    // Elvis Operator
    val foundBook2 = BookRepository().findBookByIsbn("ISBNTEST11") ?: BookDomain("ISBNTEST3", "Domain Driven Design")
    println(foundBook2)

    // Smart casts/explicit casts
    

    // Import renaming // Rename class Book en BookDomain without changing it

    // Ranges/loops

    // When expression/Sealed class

    // Inheritance (default final, open, override)

    // Delegation

    // Named parameters

    // Extensions functions

    // Standard library functions
    // Apply // returns the original instance

    // Let // Kind of map to an object

    // With

    // Run

    // Lazy

    // Use

    // Collections (Mutable, Immutable)

    // Immutability

    // Operator overloading

    // Mini DSL: if we have time

}


infix fun <T> T.shouldBeEqualTo(expected: T?): Boolean = this == expected


operator fun BookDomain.plus(book: com.riadhmnasri.domain.Book) = copy(isbn = this.isbn + book.isbn, title = this.title + book.title, price = this.price + book.price)

fun BookDomain.rate(note: Int): String = when (note) {
    in 1..5 -> "★".repeat(note)
    else -> "Invalid note"
}

fun print(obj: Any) {
    if (obj is String) {
        println(obj)
    }
}

class BookRepository {
    private val books = listOf(
            BookDomain("ISBNTEST1", "Programming Kotlin"),
            BookDomain("ISBNTEST2", "Effective Kotlin")

    )

    fun findBookByIsbn(isbn: String): BookDomain? = books.firstOrNull { it.isbn == isbn }

    fun findBookByIsbnV2(isbn: String): BookResult =
            when (val result = books.firstOrNull { it.isbn == isbn }) {
                null -> BookResult.BookNotFound(isbn)
                else -> BookResult.BookFound(result)
            }
}
