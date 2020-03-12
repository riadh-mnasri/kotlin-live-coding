package com.riadhmnasri

import org.springframework.core.io.ClassPathResource
import com.riadhmnasri.domain.Book as BookDomain

fun main() {
    /*
    Java vs Kotlin (see https://www.kotlinvsjava.com/)
    Vals vs Vars (declare variable)
    Type inference
    Class and data classes (equality) (classes instantiating (no more new))
    Default values (no need to overload)
    Referential equality and structural equality
    String templates
    Exception Handling
    Null Safety/Declare Not Nullable types/Elvis operator
    Smart casts/explicit casts
    Import renaming
    Ranges/loops
    When expression/Sealed class
    Inheritance (default final, open, override)
    Delegation
    Functions in Kotlin
    Named parameters
    Extensions functions
    Standard library functions
    Collections (Mutable/
    High order functions if we have time
     */
    // Java vs Kotlin (By example
    // Val vs Vars
    //val text = "Hello Kotlin"
    //text = "test" // Val can not reassigned
    //var text2 = "Hello Kotlin"

    // Type Inference (from variable, from interfaces)
    //val i = 1
    //println(i.plus(2))

    // Class and data classes (equality) (classes instantiating (no more new))
    //val kotlinBook = Book("ISBNTEST1", "Programming Kotlin")
    //val kotlinBook2 = Book("ISBNTEST1", "Programming Kotlin")
    //println(kotlinBook==kotlinBook2)

    // String templates
    //println("message: $text")
    /*val paragraph = """
        Hello
        Kotlin
        :-)
    """.trimIndent()*/
    //println(paragraph)

    //Default values (no need to overload)
    // See Book class constructor

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
//    val result = with(book){
//        rate(5)
//    }
//    println(result)

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
    //Collections (Mutable, Immutable)
 //   val ints = listOf(5, 2, 6, 4, 7, 9, 8) // Immutable
 //   val result = ints.asSequence().filter { it % 2 == 0 }.also { println(it) }.toList()
 //   println(result)
    // Immutable
//    val mutableListInts = mutableListOf(5, 2, 6, 4, 7, 9, 8)
//    mutableListInts.clear()
//    println(mutableListInts)
    // Operator overloading: if we have time
    // High order functions : if we have time
    // Mini DSL: if we have time

}

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




