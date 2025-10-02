package fr.diginamic.testunitaire.bo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    void shouldAddBook_WhenCalled() {
        Book book = new Book("Harry Potter", "J.K. Rowling");

        library.addBook(book);

        assertEquals(1, library.getLivres().size());
        assertTrue(library.getLivres().contains(book));
    }

    @Test
    void shouldBorrowBook_WhenCalled() {
        Book book = new Book("Harry Potter", "J.K. Rowling");

        library.addBook(book);
        boolean result = library.borrowBook("Harry Potter");

        assertTrue(result);
        assertFalse(book.isDisponible());
    }

    @Test
    void shouldNotBorrowBook_WhenBookNotFound() {
        boolean result = library.borrowBook("unknown");

        assertFalse(result);
    }

    @Test
    void shouldNotBorrowBook_WhenBookAlreadyBorrowed() {
        Book book = new Book("Harry Potter", "J.K. Rowling");

        library.addBook(book);
        library.borrowBook("Harry Potter");
        boolean result = library.borrowBook("Harry Potter");

        assertFalse(result);
        assertFalse(book.isDisponible());
    }

    @Test
    void shouldReturnBook_WhenCalled() {
        Book book = new Book("Harry Potter", "J.K. Rowling");

        library.addBook(book);
        library.borrowBook("Harry Potter");
        boolean result = library.returnBook("Harry Potter");

        assertTrue(result);
        assertTrue(book.isDisponible());
    }

    @Test
    void shouldNotReturnBook_WhenBookNotFound() {
        assertFalse(library.returnBook("unknown"));
    }

    @ParameterizedTest
    @CsvSource({"0, 0, 0", "5, 2, 3", "5, 5, 0"})
    void shouldCountAvailableBooks_WhenCalled(int nbToAdd, int nbToBorrow, int expected) {
        // Ajout des livres
        for (int i = 1; i <= nbToAdd; i++) {
            library.addBook(new Book("Book " + i, "Author " + i));
        }

        // Emprunt des livres
        for (int i = 1; i <= nbToBorrow; i++) {
            library.borrowBook("Book " + i);
        }

        assertEquals(expected, library.countAvailableBooks());
    }

    @Test
    void shouldNotAddBook_WhenTitleAlreadyExistsOrNull() {
        Book book = new Book("Harry Potter", "J.K. Rowling");
        Book bookNull = null;

        library.addBook(book);

        assertThrows(IllegalArgumentException.class, () -> library.addBook(book));
        assertThrows(IllegalArgumentException.class, () -> library.addBook(bookNull));
    }

    @Test
    void shouldRejectInvalidBook() {
        assertThrows(IllegalArgumentException.class, () -> new Book("", "Auteur"));
        assertThrows(IllegalArgumentException.class, () -> new Book("Titre", ""));
    }
}
