package models.typeCaracole;

public enum EmplacementCarte {
    E1("Joueur 1"),
    E2("Joueur 2"),
    E3("Joueur 3"),
    E4("Joueur 4"),
    E5("Joueur 5"),
    DEFAUSSE("Défausse"),
    PIOCHE("Pioche");

    private String emplacement;
    private static final int size = EmplacementCarte.values().length;

    EmplacementCarte(String emplacement){
        this.emplacement = emplacement;
    }

    public String getEmplacement() {
        return emplacement;
    }
    public static int getSize() { return size;}
}
