package com.riadhmnasri


fun main() {
    // Java vs Kotlin (By example)
    // Val vs Vars


    // Type Inference (from variable, from interfaces)


    // Class and data classes (equality) (classes instantiating (no more new))
    // Referential equality and structural equality


    // String templates

    // String paragraph


    // Default values (no need to overload)
    // See Book class constructor


    // Exception (all are unchecked exceptions)/annotation @Throws when kotlin function called from Java
    // All exceptions are unchecked

    // Null Safety/Declare Not Nullable types/Elvis operator
    // Null Safety


    // Elvis Operator


    // Smart casts/explicit casts

    // Import renaming // Rename class Book en BookDomain without changing it
    // Done

    // Ranges/loops


    // When expression/Sealed class

    // Inheritance (default final, open, override)

    // Delegation

    // Named parameters

    // Extensions functions


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
