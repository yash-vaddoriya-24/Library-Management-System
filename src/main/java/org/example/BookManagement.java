package org.example;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

interface BookManager {
    boolean addBookDetails(Library library, Map<String, Map<String, String>> bookDetails);
    boolean borrowBook(String title, Map<String, Map<String, String>> bookOfLib);
}

class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}

class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}

class BookValidator {
    public void validateBook(Library book) {
        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty() || book.getPublisherYear().isEmpty()) {
            throw new IllegalArgumentException("Book values cannot be empty");
        }

        LocalDate localDate = LocalDate.now();
        if (Integer.parseInt(book.getPublisherYear()) > localDate.getYear()) {
            throw new IllegalArgumentException("Publisher year cannot be in the future");
        }
    }
}

public class BookManagement implements BookManager {
    private final BookValidator validator = new BookValidator();

    //Add Functionality of LibraryManagementSystem
    public boolean addBookDetails(Library book, Map<String, Map<String, String>> bookOfLib) {
        LocalDate localDate = LocalDate.now();
        String isbn = book.generateISBN();
        int currentSize = bookOfLib.size();

        validator.validateBook(book);
        // Ensure the ISBN does not already exist
        if (bookOfLib.containsKey(isbn)) {
            throw new BookAlreadyExistsException("Book with the same ISBN already exists.");
        }

        // Validate publisher year
        if (Integer.parseInt(book.getPublisherYear()) > localDate.getYear()) {
            throw new RuntimeException("Publisher year cannot be in the future.");
        }
        Map<String, String> bookDetails = new HashMap<>();
        bookDetails.put("title", book.getTitle().trim().toLowerCase());
        bookDetails.put("author", book.getAuthor().trim());
        bookDetails.put("publisherYear", book.getPublisherYear().trim());
        bookDetails.put("isBorrow", "false"); // Initially not borrowed

        bookOfLib.put(isbn, bookDetails);
        if (currentSize + 1 == bookOfLib.size()) {
            return true; // Indicating success
        }
        throw new RuntimeException("Error while adding new book");
    }

    //Borrow Functionality of LibraryManagementSystem and as well as Update in Library Book
    public boolean borrowBook(String title, Map<String, Map<String, String>> bookOfLib) {
        title = title.trim().toLowerCase();
        boolean bookFound = false;

        for (Map.Entry<String, Map<String, String>> entry : bookOfLib.entrySet()) {
            Map<String, String> bookDetails = entry.getValue();

            if (title.equals(bookDetails.get("title"))) {
                bookFound = true;

                if ("false".equals(bookDetails.get("isBorrow"))) {
                    bookDetails.put("isBorrow", "true");
                    bookOfLib.put(entry.getKey(), bookDetails);
                    return true; // Successfully borrowed
                }
            }
        }

        if (!bookFound) {
            throw new BookNotFoundException("Book with the given title not found.");
        } else {
            throw new BookNotFoundException("All copies of this book are currently borrowed.");
        }
    }
}
