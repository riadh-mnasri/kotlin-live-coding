package com.riadhmnasri

import com.riadhmnasri.domain.Book
import org.springframework.core.io.ClassPathResource

fun main() {
    // Java vs Kotlin (By example)
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
    val kotlinBook = Book("ISBNTEST1", "Hello Kotlin", 30.0)
    val kotlinBook2 = Book("ISBNTEST1", "Hello Kotlin")
    println(kotlinBook === kotlinBook2)

    // String templates
    println("Display $text")

    // String paragraph
    val json = """
        {
        "message": "Hello Kotlin"
        }
    """.trimIndent()
    println(json)


    // Default values (no need to overload)
    // See Book class constructor
    val bookKotlin2 = Book("ISBNTEST2", "Programming Kotlin")
    println(bookKotlin2)


    // Exception (all are unchecked exceptions)/annotation @Throws when kotlin function called from Java
    // All exceptions are unchecked

    // Null Safety/Declare Not Nullable types/Elvis operator
    // Null Safety
    val result = BookRepository().findBookByIsbn("ISBNTEST1")
    println(result?.title)

    // Elvis Operator
    val result2 = BookRepository().findBookByIsbn("ISBNTESTT1") ?: Book("ISBNTEST3", "Domain Driven Design")
    println(result2.title)


    // Smart casts/explicit casts
    print("Text")

    // Import renaming // Rename class Book en BookDomain without changing it


    // Ranges/loops
    val range = 1..10
    for (i in range) {
        println(i)
    }


    // When expression/Sealed class
    val resultSearch = BookRepository().findBookByIsbnV2("ISBNTEST1")
    println(processResult(resultSearch))


    // Inheritance (default final, open, override)
    println(Car().name())

    // Delegation
    val activity = Activity(Tennis())
    println(activity.name() + " - " + activity.cost())

    // Named parameters
    val bookJava = Book(title = "Hello Java", isbn = "ISBNTEST3", price = 35.0)
    println(bookJava)

    // Extensions functions
    println(kotlinBook.rate(5))

    // Standard library functions
    // Apply // returns the original instance
    kotlinBook.apply { println(rate(5)) }

    // Let // Kind of map to an object
    val titleUppedCase = kotlinBook.let { it.title.toUpperCase() }
    println(titleUppedCase)

    // With // return the processed result


    // Run


    // Lazy
    val rateBookNotNow = lazy { kotlinBook.rate(5) }
    println("Lazy :: ${rateBookNotNow.value}")


    // Use
    val readText = ClassPathResource("/data/content.txt").inputStream.use { it.bufferedReader().readText() }
    println(readText)

    // Collections (Mutable, Immutable)
    val ints =  listOf(6, 9, 8, 5, 4)
    val evens = ints.asSequence().filter { it%2==0}.toList()
    println(evens)


    // Mutable collections
    val ints2 = mutableListOf(5, 8, 9)
    ints2.clear()
    println(ints2)


    // Operator overloading: if we have time
    println(kotlinBook + bookJava)


    // Mini DSL: if we have time
    println("*****" shouldBeEqualTo kotlinBook.rate(4))

}

infix fun <T> T.shouldBeEqualTo(expected: T?): Boolean = this == expected

fun Book.rate(note: Int): String {
    return when (note) {
        note -> "*".repeat(note)
        else -> "Invalid note"
    }
}

operator fun Book.plus(bookToAdd: Book): Book {
    return Book(this.isbn + bookToAdd.isbn, this.title + bookToAdd.isbn, this.price + bookToAdd.price)
}
fun processResult(bookResult: BookResult): String {
    return when (bookResult) {
        is BookResult.BookFound -> "Cool !"
        is BookResult.BookNotFound -> "Sorry, may be next time !"
    }
}

fun print(obj: Any) {
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
