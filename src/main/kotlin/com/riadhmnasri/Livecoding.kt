package com.riadhmnasri

import org.springframework.core.io.ClassPathResource
import com.riadhmnasri.domain.Book as BookDomain

fun main() {
    // Java vs Kotlin (By example
    // Val vs Vars
    val text = "Hello Kotlin"
    //text = "test" // Val can not reassigned
    var text2 = "Hello Kotlin"

    // Type Inference (from variable, from interfaces)
    val i = 1
    println(i.plus(2))

    // Class and data classes (equality) (classes instantiating (no more new))
    val kotlinBook = BookDomain("ISBNTEST1", "Programming Kotlin")
    val kotlinBook2 = BookDomain("ISBNTEST1", "Programming Kotlin")
    println(kotlinBook == kotlinBook2)

    // String templates
    println("message: $text")
    val paragraph = """
        Hello
        Kotlin
        :-)
    """.trimIndent()
    println(paragraph)

    // Default values (no need to overload)
    // See Book class constructor

    // Referential equality and structural equality
    val kotlinBook3 = BookDomain("ISBNTEST1", "Programming Kotlin")
    val kotlinBook4 = BookDomain("ISBNTEST1", "Programming Kotlin")
    println(kotlinBook3 === kotlinBook4)

    // Exception (all are unchecked exceptions)/annotation @Throws when kotlin function called from Java
    // All exceptions are unchecked

    // Null Safety/Declare Not Nullable types/Elvis operator

    // Null Safety
    val foundBook = BookRepository().findBookByIsbn("ISBNTESTTTT")
    println("Found book: ${foundBook?.title}")

    // Elvis Operator
    val result1 = BookRepository().findBookByIsbn("ISBN") ?: BookDomain("ISBNTEST3", "Domain Driven Design")
    println(result1)

    // Smart casts/explicit casts
    print("Text")

    // Import renaming // Rename class Book en BookDomain without changing it
    val bookDomain = BookDomain("ISBNTEST1", "Programming Kotlin")

    // Ranges/loops
    val rangInts = 1..10 //1 until 10
    for (i in rangInts) {
        println(i)
    }

    // When expression/Sealed class
    val resultSearch = BookRepository().findBookByIsbnV2("ISBNTEST1")
    when (resultSearch) {
        is BookResult.BookNotFound -> println("Sorry, Not found!")
        is BookResult.BookFound -> println("Cool !")
    }

    // Inheritance (default final, open, override)
    println(Car().name())

    // Delegation
    val activity = Activity(Tennis())
    println("${activity.name()}-${activity.cost()}")

    // Named parameters
    val resultSearch2 = BookRepository().findBookByIsbnV2(isbn = "ISBNTEST1")
    println(resultSearch2)
    val bookSample = BookDomain(isbn = "ISBNTEST1", title = "Programming Kotlin", price = 30.0)
    println(bookSample)

    // Extensions functions
    val book10 = BookDomain("ISBNTEST1", "Programming Kotlin")
    println(book10.rate(5))

    // Standard library functions
    // Apply // returns the original instance
    val book2 = BookDomain("ISBNTEST1", "Programming Kotlin").apply { println("rate: ${rate(5)}") }
    println(book2)

    // Let // Kind of map to an object
    val book3 = BookDomain("ISBNTEST1", "Programming Kotlin").let { it.rate(5) }
    println(book3)

    // With
    val book4 = BookDomain("ISBNTEST1", "Programming Kotlin")
    val noteBook4 = with(book4) {
        rate(5)
    }
    println(noteBook4)

    // Run
    val book6 = BookDomain("ISBNTEST1", "Programming Kotlin")
    val noteBook6 = book6.run {
        rate(5)
    }
    println(noteBook6)

    // Lazy
    val note = lazy { BookDomain("ISBNTEST1", "Programming Kotlin").rate(5) }
    println(note.value)

    // Use
    val text3 = ClassPathResource("/data/content.txt").inputStream.use { it.bufferedReader().readText() }
    println(text3)

    // Collections (Mutable, Immutable)
    val ints = listOf(5, 2, 6, 4, 7, 9, 8) // Immutable
    val result13 = ints.asSequence().filter { it % 2 == 0 }.also { println(it) }.toList()
    println(result13)

    // Immutability
    val mutableListInts = mutableListOf(5, 2, 6, 4, 7, 9, 8)
    mutableListInts.clear()
    println(mutableListInts)

    // Operator overloading
    val kotlinBookToAdd = BookDomain("ISBNTEST1", "Programming Kotlin", 30.0)
    val dddBookToAdd = BookDomain("ISBNTEST2", "DDD", 40.0)
    println(kotlinBookToAdd + dddBookToAdd)

    // Mini DSL: if we have time
    println("★★★★" shouldBeEqualTo BookDomain("ISBNTEST1", "Programming Kotlin", 30.0).rate(5))

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