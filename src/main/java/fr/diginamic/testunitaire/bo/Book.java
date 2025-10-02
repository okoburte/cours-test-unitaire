package fr.diginamic.testunitaire.bo;

import java.util.Objects;

public class Book {
    private String titre;
    private String auteur;
    private boolean disponible = true;

    public Book(String titre, String auteur) {
        if (titre == null || titre.isBlank()) {
            throw new IllegalArgumentException("titre obligatoire");
        }
        if (auteur == null || auteur.isBlank()) {
            throw new IllegalArgumentException("auteur obligatoire");
        }
        this.titre = titre;
        this.auteur = auteur;
    }

    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public boolean isDisponible() { return disponible; }

    public void emprunter() { this.disponible = false; }
    public void retourner() { this.disponible = true; }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book b)) return false;
        return Objects.equals(titre, b.titre) && Objects.equals(auteur, b.auteur);
    }
    @Override
    public int hashCode() { return Objects.hash(titre, auteur); }
}
