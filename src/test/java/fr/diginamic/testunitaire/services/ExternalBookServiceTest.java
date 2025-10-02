package fr.diginamic.testunitaire.services;

import fr.diginamic.testunitaire.bo.Book;
import fr.diginamic.testunitaire.bo.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExternalBookServiceTest
{
    @Mock
    ExternalBookService externalBookService;

    @InjectMocks
    Library library;

     @BeforeEach
     void setUp() {
         library = new Library();
         library.setExternalService(externalBookService); // injection manuelle ANTI-NPE
     }

    @Test
    void shouldReturnTrue_WhenBookIsAvailable() {
        when(externalBookService.isBookAvailable("Harry Potter")).thenReturn(true);

        boolean result = library.checkExternalAvailability("Harry Potter");

        assertTrue(result);
        verify(externalBookService, times(1)).isBookAvailable("Harry Potter");
    }

    @Test
    void shouldAddBookToLibrary_WhenBookIsAdded() {
        when(externalBookService.fetchBookDetails("Harry Potter")).thenReturn(new Book("Harry Potter", "J.K. Rowling"));

        library.importBookFromExternal("Harry Potter");

        verify(externalBookService, times(1)).fetchBookDetails("Harry Potter");
        verify(library, times(1)).addBook(any(Book.class));
    }

    @Test
    void shouldThrowException_WhenBookNotFound() {
        when(externalBookService.fetchBookDetails("unknown")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> library.importBookFromExternal("unknown"));
        verify(externalBookService, times(1)).fetchBookDetails("unknown");
        verify(library, never()).addBook(any(Book.class));
    }

    @Test
    void shouldCallMultipleTimes() {
        when(externalBookService.isBookAvailable(anyString())).thenReturn(true);

        library.checkExternalAvailability("Harry Potter");
        verify(externalBookService, times(1)).isBookAvailable(anyString());

        library.checkExternalAvailability("Harry Potter 2");
        verify(externalBookService, times(2)).isBookAvailable(anyString());

        library.checkExternalAvailability("Harry Potter 3");
        verify(externalBookService, times(3)).isBookAvailable(anyString());
    }
}
