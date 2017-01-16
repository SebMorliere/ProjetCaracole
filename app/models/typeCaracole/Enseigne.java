package models.typeCaracole;

public enum Enseigne {
    PIQUE("pique", "noir", "1F0A"),
    COEUR("coeur", "rouge", "1F0B"),
    CARREAU("carreau", "rouge", "1F0C"),
    TREFFLE("treffle", "noir", "1F0D");

    private String enseigne, couleur, unicodePre;

    Enseigne(String enseigne, String couleur, String unicodePre){
        this.enseigne = enseigne;
        this.couleur = couleur;
        this.unicodePre = unicodePre;
    }

    public String getEnseigne() {
        return enseigne;
    }
    public String getCouleur(){
        return couleur;
    }
    public String getUnicodePre(){
        return unicodePre;
    }
}
