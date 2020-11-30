package com.riadhmnasri

import org.springframework.core.io.ClassPathResource
import com.riadhmnasri.domain.Book as BookDomain

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
    print("Text")

    // Import renaming // Rename class Book en BookDomain without changing it
    // Done

    // Ranges/loops
    val range = 1..10
    for(i in range){
        println(i)
    }

    // When expression/Sealed class
    val resulSearch = BookRepository().findBookByIsbnV2("ISBNTEST1")
    println(processResult(resulSearch))

    // Inheritance (default final, open, override)
    println(Car().name())

    // Delegation
    val activity = Activity(Tennis())
    println(activity.name() + " - " + activity.cost())

    // Named parameters
    val bookSample = BookDomain(title = "Effective kotlin", isbn= "ISBNTEST1")
    println(bookSample)

    // Extensions functions
    println(BookDomain(title = "Effective kotlin", isbn= "ISBNTEST1").rate(5))

    // Standard library functions
    // Apply // returns the original instance
    bookKotlin.apply { println(rate(5)) }

    // Let // Kind of map to an object
    println(bookKotlin.let { it.title })

    // With // return the processed result
    val rate = with(bookKotlin){
        bookKotlin.rate(5)
    }
    println(rate)

    // Run
    val rate2 = bookKotlin.run {
        bookKotlin.rate(5)
    }
    println(rate2)

    // Lazy
    val rate3 = lazy { bookKotlin.rate(5)}
    println(rate3.value)

    // Use
    val readText = ClassPathResource("/data/content.txt").inputStream.use { it.bufferedReader().readText() }
    println(readText)

    // Collections (Mutable, Immutable)
    val ints = listOf(6, 7, 8, 9, 3, 4, 5)
    val filteredResult = ints.asSequence().filter { it % 2 == 0 }.toList()
    println(filteredResult)

    // Mutable collections
    val mutableList = mutableListOf(7, 8, 9, 6, 4, 3)
    mutableList.clear()
    println(mutableList)

    // Operator overloading
    val book1 = BookDomain(title = "Effective kotlin", isbn= "ISBNTEST1", price = 30.0)
    val book2 =  BookDomain(title = "Effective Java", isbn= "ISBNTEST1", price = 50.0)
    println(book1 + book2)

    // Mini DSL: if we have time
    println("★★★★" shouldBeEqualTo BookDomain("ISBNTEST1", "Programming Kotlin", 30.0).rate(5))

}

infix fun <T> T.shouldBeEqualTo(expected: T?): Boolean = this == expected

fun BookDomain.rate(note:Int): String{
    return when(note){
        note -> "*".repeat(note)
        else -> "Invalid note"
    }
}

operator fun BookDomain.plus(bookToAdd: BookDomain): BookDomain {
    return BookDomain(this.isbn + bookToAdd.isbn, this.title+bookToAdd.isbn, this.price+bookToAdd.price)
}

fun processResult(bookResult: BookResult): String {
    return when(bookResult){
        is BookResult.BookFound ->  "Cool !"
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
