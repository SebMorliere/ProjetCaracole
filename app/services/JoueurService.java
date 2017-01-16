package services;

import models.exception.CaracoleException;
import models.JoueurCaracole;
import models.typeCaracole.EmplacementJoueur;
import play.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class JoueurService {
    /**
     * Singleton
     */
    private static JoueurService instance;
    /**
     * Constructeur privé
     */
    private JoueurService() {
    }
    /**
     * Seule méthode pour récupérer une instance (toujours la même)
     *
     * @return toujours la même instance
     */
    public static JoueurService get() {
        if (instance == null) {
            instance = new JoueurService();
        }
        return instance;
    }

    private static int emplacementPris = 0;
    private static List<JoueurCaracole> joueurs;
    private static JoueurCaracole joueur;

    public static JoueurCaracole addPlayer(String pseudo) throws CaracoleException {
        Logger.info("addPlayer:");
        if(isPlateauFull()){
            throw new CaracoleException("Plus de place disponible sur le plateau !");
        }
        joueur = new JoueurCaracole();
        joueur.pseudo = pseudo;

        joueur.emplacement = EmplacementJoueur.values()[emplacementPris];
        emplacementPris++;
        joueurs.add(joueur);
        return joueur;
    }

    public static List<JoueurCaracole> findPlayers(){
        return joueurs;
    }

    public static void clearJoueurs(){
        joueur = null;
        joueurs = null;
    }

    public static boolean isPlateauFull(){
        Logger.info("isPlateauFull:");
        if(emplacementPris >= EmplacementJoueur.getSize()){
            return true;
        }
        return false;
    }
    //TODO ajouter une fonction pour retirerJoueur() et retirerTous()
}
