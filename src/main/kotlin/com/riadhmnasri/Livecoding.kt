package com.riadhmnasri

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

    // Class and data classes (classes instantiating (no more new))`
    val kotlinBook = Book("ISBNTEST1", "Programming Kotlin")
    val kotlinBook2 = Book("ISBNTEST1", "Programming Kotlin")
    println(kotlinBook === kotlinBook2)
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


data class Book(val isbn: String, val title: String, val price: Double = 0.0)
