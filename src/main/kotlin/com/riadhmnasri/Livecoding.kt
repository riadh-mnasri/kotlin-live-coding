package com.riadhmnasri

import org.springframework.core.io.ClassPathResource
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

    // Type Inference (from variable, from interfaces)


    // Class and data classes (classes instantiating (no more new))
    // Referential equality and structural equality

    // Default values (no need to overload)
    // Example with Book class constructor

    // String templates


    // Referential equality and structural equality

    // Exception handling (all are exceptions unchecked )/annotation

    // Null Safety/Declare Not Nullable types/Elvis operator
    // Functions in Kotlin
    // Null Safety

    // Elvis Operator

    // Smart casts/explicit casts

    // Import renaming

    // Ranges/loops

    // When expression/Sealed class

    // Inheritance (default final, open, override)

    // Delegation

    // Functions in Kotlin
    // Already spoken about

    // Named parameters

    // Extensions functions

    // Standard library functions
    // Apply // returns the original instance

    // Let // it's a kind of map to an object

    // With

    // Run

    // Lazy

    // Use

    // Collections (Mutable, Immutable)
    // Immutable collections

    // Mutable collections

    // Operator overloading: if we have time

    // Mini DSL: if we have time

}


infix fun <T> T.shouldBeEqualTo(expected: T?) = this == expected


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




