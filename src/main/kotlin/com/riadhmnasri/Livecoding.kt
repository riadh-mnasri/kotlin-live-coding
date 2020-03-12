package com.riadhmnasri

import com.riadhmnasri.domain.Book as BookDomain

fun main() {
    /*
    What I will present in this live coding session ?
    Java vs Kotlin (see https://www.kotlinvsjava.com/)
    Vals vs Vars (how to declare variable)
    Type inference
    Class and data classes (classes instantiating (no more new))
    Referential equality and structural equality
    Default values (no need to overload)
    String templates
    Exception Handling
    Null Safety: Declare Not Nullable types, Elvis operator, ...
    Smart casts/explicit casts
    Import renaming
    Ranges/loops
    When expression/Sealed class
    Inheritance (by default: final -> open, override)
    Delegation
    Functions in Kotlin
    Named parameters
    Extensions functions
    Standard library functions
    Collections (Mutable/Immutable)
    Operator overloading: if we have time
    Mini DSL: if we have time
     */

    // Java vs Kotlin (By example)
    // Val vs Vars
    //val text = "Hello Kotlin"
    //text = "test" // Val can not reassigned
    //var text2 = "Hello Kotlin"

    // Type Inference (from variable, from interfaces)
    //val i = 1
    //println(i.plus(2))

    // Class and data classes (classes instantiating (no more new))
    //Referential equality and structural equality
    //val kotlinBook = Book("ISBNTEST1", "Programming Kotlin")
    //val kotlinBook2 = Book("ISBNTEST1", "Programming Kotlin")
    //println(kotlinBook==kotlinBook2)

    //Default values (no need to overload)
    // See Book class constructor

    // String templates
    //val text = "Hello Kotlin"
    //println("message: $text")
    /*val paragraph = """
        Hello
        Kotlin
        :-)
    """.trimIndent()*/
    //println(paragraph)


    //Referential equality and structural equality
    /* val kotlinBook = BookDomain("ISBNTEST1", "Programming Kotlin")
     val kotlinBook2 = BookDomain("ISBNTEST1", "Programming Kotlin")
     println(kotlinBook===kotlinBook2) */
    // Exception (all are unchecked exceptions)/annotation
    // All exceptions are unchecked

    //Null Safety/Declare Not Nullable types/Elvis operator

    // Null Safety
    //val foundBook = BookRepository().findBookByIsbn("ISBNTESTTTT")
    //println("found book: ${foundBook?.title}")

    // Elvis Operator
    //val result = BookRepository().findBookByIsbn("ISBN") ?: Book("ISBNTEST3", "Domain Driven Design")
    //println(result)

    // Smart casts/explicit casts
    //print("Text")

    // Import renaming
    //val bookDomain = BookDomain("ISBNTEST1", "Programming Kotlin")

    // Ranges/loops
    /* val rangInts = 1..10 //1 until 10
    for (i in rangInts){
        println(i)
    } */

    // When expression/Sealed class
    /*val result = BookRepository().findBookByIsbnV2("ISBNTEST1")
    when (result) {
        is BookResult.BookNotFound -> println("Sorry, Not found!")
        is BookResult.BookFound -> println("Cool !")
    }*/

    // Inheritance (default final, open, override)
    //println(Car().name())
    //Delegation
    /* val activity = Activity(Tennis())
    println("${activity.name()}-${activity.cost()}")*/
    //Functions in Kotlin
    // Already spoken about
    // Named parameters
    /* val result = BookRepository().findBookByIsbnV2(isbn = "ISBNTEST1")
    val bookSample = BookDomain(isbn="ISBNTEST1", title = "Programming Kotlin", price = 30.0 )
    println(bookSample)*/
    // Extensions functions
    //val book = BookDomain("ISBNTEST1", "Programming Kotlin")
    //println(book.rate(5))
    //Standard library functions
    // Apply // returns the original instance
    //val book = BookDomain("ISBNTEST1", "Programming Kotlin").apply { println("rate: ${rate(5)}") }
    //println(book)
    // Let // Kind of map to an object
    //val book = BookDomain("ISBNTEST1", "Programming Kotlin").let { it.rate(5) }
    //println(book)
    // With
    //val book = BookDomain("ISBNTEST1", "Programming Kotlin")
    //val result = with(book){
    //    rate(5)
    // }
    //println(result)

    // Run
    //    val book = BookDomain("ISBNTEST1", "Programming Kotlin")
    //    val result = book.run {
    //        rate(5)
    //    }
    //    println(result)
    // Lazy
    // val note = lazy { BookDomain("ISBNTEST1", "Programming Kotlin").rate(5) }
    //println(note.value)

    // Use
    //val text = ClassPathResource("/data/content.txt").inputStream.use { it.bufferedReader().readText() }
    //println(text)

    // Collections (Mutable, Immutable)
    // Immutable collections
    // val ints = listOf(5, 2, 6, 4, 7, 9, 8) // Immutable
    // val result = ints.asSequence().filter { it % 2 == 0 }.also { println(it) }.toList()
    // println(result)

    // Mutable collections
    // val mutableListInts = mutableListOf(5, 2, 6, 4, 7, 9, 8)
    // mutableListInts.clear()
    // println(mutableListInts)
    // Operator overloading: if we have time
/*    val kotlinBook = BookDomain("ISBNTEST1", "Programming Kotlin", 30.0)
    val dddBook = BookDomain("ISBNTEST2", "DDD", 40.0)
    println(kotlinBook+dddBook)*/
    // Mini DSL: if we have time
    //println("★★★★" shouldBeEqualTo BookDomain("ISBNTEST1", "Programming Kotlin", 30.0).rate(5))

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

//  Sealed classes
sealed class BookResult {
    class BookNotFound(isbn: String) : BookResult()
    class BookFound(book: BookDomain) : BookResult()
}

// Inheritance
open class Vehicle {
    open fun name(): String {
        return "Vehicle"
    }
}

class Car : Vehicle() {
    override fun name(): String {
        return "Car"
    }
}


// Delegation
interface Hobby {
    fun name(): String
    fun cost(): String
}

class Tennis : Hobby {
    override fun name(): String = "Play Tennis"
    override fun cost(): String = "Expensive"
}


class Activity(hobby: Hobby) : Hobby by hobby {
    override fun name(): String = "Activity :: Play Tennis"
}




