package models.typeCaracole;

public enum EmplacementJoueur {
    J1("Joueur 1"),
    J2("Joueur 2"),
    j3("Joueur 3"),
    j4("Joueur 4"),
    j5("Joueur 5");

    private String emplacement;
    private static final int size = EmplacementJoueur.values().length;

    EmplacementJoueur(String emplacement){
        this.emplacement = emplacement;
    }

    public String getEmplacement() {
        return emplacement;
    }
    public static int getSize() { return size;}
}
