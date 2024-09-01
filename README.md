
# Library Management System

## Objectives

Create a simple library management system that allows users to perform basic operations such as adding books, borrowing books, returning books, and viewing available books.



## Requirements


- Add Books:

    - Users should be able to add new books to the library
    - Each book should have a unique identifier (e.g., ISBN), title, author, and publication year.

- Borrow Books:

    - Users should be able to borrow a book from the library.
    - The system should ensure that the book is available before allowing it to be borrowed.
    - If the book is not available, the system should raise an appropriate error.

- Return Books:

    - Users should be able to return a borrowed book.
   - he system should update the availability of the book accordingly.

- View Available Books:

    - Users should be able to view a list of all available books in the library.


## Table of Contents
- [Technologies](#TechnologiesUsed)
- [Features](#Features)
- [Project Structure](#ProjectStructure)
- [Setup](#setup)
- [Usage/Examples](#Usage/Examples)
- [Testing](#Testing)
- [Contributing](#Contributing)
- [Documentation](#Documentation)
- [Lesson Learned](#LessonLearned)
- [License](#LICENSE)


## Technologies Used

 - Core Java
 - Collections Framework
 - OOP Concepts
 - JUnit Testing Framework
 - Version Control (Git, Github)
 - ChatGPT


## Features

 - Add Book: Add new books with details such as title, author, and publisher year.
 - Borrow Book: Borrow available books and track their status.
 - View Books: List all available and borrowed books.
 - Search Book: User Can Search Book details using title of book.
 - return Borrow Book: User can also return Borrow book by giving title of book.
 - Exception Handling: Handle All Exception cases where books are not found or already borrowed.
## Project Structure

 - Library.java: Defines the book's attributes and methods.
 - BookManager.java: Interface for managing book operations.
 - BookManagement.java: Implements book management and borrowing functionalities.
 - BookRepo.java: Interface for Viewing and Searching book details.
 - viewBooks.java: Implements viewing available, borrowed books and also Search Book details.
 - LibraryTest.java: Contains unit tests for the system.
## Setup

 - Java 11 or higher: Ensure Java is installed and set up properly on your machine.
 - JUnit 5: For running tests.
## Usage/Examples

 - Add Book:
    ```java
    Library library = new Library("Book Title", "Author Name", "2022");
    BookManager bm = new BookManagement();
    bm.addBookDetails(library, LibraryBook);

-   Borrow Book:
    ```java
    bm.borrowBook("Book Title", LibraryBook);

- View Available Books:
    ```java
    BookRepo br = new viewBooks();
    br.viewAvailableBooks(LibraryBook);
- View Borrowed Books:
    ```java
    br.viewBorrowedBooks(LibraryBook);
- return Borrowed Books:
    ```java
    br.returnBooks(title,LibraryBook);

## Testing

- Compile Test Classes:
```bash
javac -d bin -cp bin:test src/test/org/example/*.java
```

- Run Test Cases:
```bash
java -cp bin:test org.junit.runner.JUnitCore org.example.LibraryTest
```



## Contributing

1. Fork the Repository.
2. Create a New Branch
 ```bash
 git checkout -b feature/your-feature
```
3. Commit Your Changes:
```bash
git commit -am 'Add new feature'
```
4. Push to the Branch:
```bash
git push origin feature/your-feature
```
5. Create a Pull Request:


## Documentation

[Core Java](https://docs.oracle.com/en/java/)\
[Collections Framework](https://download.java.net/java/early_access/panama/docs/api/java.base/java/util/doc-files/coll-index.html)\
[Junit Framework](https://junit.org/junit5/)\
[Github](https://docs.github.com/en)\
[Git](https://git-scm.com/doc)



## Lessons Learned

- Effective use of OOP Concepts and Collections Framework
- SOLID principle
- Exception Handling
- Unit Testing with JUnit
- Version Control Practices(Git, Github)
- Documentation Skills
- Project Management
## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.

