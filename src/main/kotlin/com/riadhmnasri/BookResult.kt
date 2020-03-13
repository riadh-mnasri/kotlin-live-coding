package com.riadhmnasri

import com.riadhmnasri.domain.Book

//  Sealed classes
sealed class BookResult {
    class BookNotFound(isbn: String) : BookResult()
    class BookFound(book: Book) : BookResult()
}