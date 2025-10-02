package fr.diginamic.testunitaire.services;

import fr.diginamic.testunitaire.bo.Book;

public interface ExternalBookService {
    boolean isBookAvailable(String title);
    Book fetchBookDetails(String title);
}