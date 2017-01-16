package models.typeCaracole;

public enum Valeur {
    AS      ("as", 1  ,"1"),
    DEUX    ("2" , 2  ,"2"),
    TROIS   ("3" , 3  ,"3"),
    QUATRE  ("4" , 4  ,"4"),
    CINQ    ("5" , 5  ,"5"),
    SIX     ("6" , 6  ,"6"),
    SEPT    ("7" , 7  ,"7"),
    HUIT    ("8" , 8  ,"8"),
    NEUF    ("9" , 9  ,"9"),
    DIX     ("10", 10 ,"A"),
    VALET   ("valet" , 10 ,"B"),
    DAME    ("dame" , 10 ,"D"),
    ROI     ("roi" , 10 ,"E"),
    JOKER   ("joker", 0, "F");

    private String nom;
    private Integer point;
    private String unicodePost;

    Valeur(String nom, Integer point, String unicodePost){
        this.nom = nom;
        this.point = point;
        this.unicodePost = unicodePost;
    }

    public String getNom() {
        return nom;
    }

    public Integer getPoint() {
        return point;
    }

    public String getUnicodePost() {
        return unicodePost;
    }
}
