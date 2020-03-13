package com.riadhmnasri

fun main() {
    // Java vs Kotlin (By example)
    // Val vs Vars

    var i = 1
    println(i.plus(3))
    i = 2

    // Type Inference (from variable, from interfaces)


    // Class and data classes (equality) (classes instantiating (no more new))
    // String templates
    val text = "Hello"
    //println("$text Kotlin")
    val paragraph =
            """
        {
        "name": "valeur"
        }
          """.trimIndent()
    println(paragraph)
    //Default values (no need to overload)



    //Referential equality and structural equality

    // Exception (all are unchecked exceptions)/annotation @Throws


    // Null Safety/Declare Not Nullable types/Elvis operator//Functions usage
    // Null Safety

    val result = BookRepository().findByIsbn("TOTO")
  when(result){
      is BookResult.BookFound -> println("Cool: ${result.book.title}")
  }

    // Elvis Operator

    // Smart casts/explicit casts

    // Import renaming


    // Ranges/loops


    // When expression/Sealed class


    // Inheritance (default final, open, override)


    // Delegation

    // Named parameters


    // Extensions functions

    val bookKotlin = Book(title= "Programming Kotlin", isbn= "ISBNTEST1")
    println(bookKotlin.rate(5))

    //Standard library functions
    // Apply // returns the original instance

    // Let // Kind of map to an object


    // With


    // Run


    // Lazy


    // Use


    // Collections (Mutable, Immutable)


    // Immutability


    // Operator overloading: if we have time


    // Mini DSL: if we have time


}

class BookRepository {
    private val books = listOf(
            Book("ISBNTEST1", "Programming Kotlin"),
            Book("ISBNTEST2", "Effective Kotlin")

    )

    fun findByIsbn(isbn: String): BookResult {
        val result = books.firstOrNull { it.isbn == isbn}
        return when(result){
            null -> BookResult.BookNotFound(isbn)
            //
            else -> BookResult.BookFound(result)
        }
    }

}

sealed class BookResult{
    class BookNotFound(val ibsn: String): BookResult()
    class BookFound(val book: Book): BookResult()
}



data class Book(val isbn: String, val title: String, val price: Double = 0.0)

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


class Running : Hobby {
    override fun name(): String = "Running"
    override fun cost(): String = "Expensive"
}



class Activity(hobby: Hobby, text: CharSequence) : Hobby by hobby, CharSequence by text {
    override fun name(): String = "Activity :: Play Tennis"
}

fun Book.rate(note: Int) = when(note){
    in 1..5 -> "★".repeat(note)
    else -> "Invalid note"
}