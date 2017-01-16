package services;

import models.CarteCaracole;
import models.exception.CaracoleException;
import models.typeCaracole.*;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

public class DeckCaracole {
    private static List<CarteCaracole> deck = null;
    public static final int nombreCartes = 52;

    /**
     * Singleton
     */
    private static DeckCaracole instance;

    /**
     * Constructeur privé
     */
    private DeckCaracole() {
    }

    /**
     * Seule méthode pour récupérer une instance (toujours la même)
     *
     * @return toujours la même instance
     */
    public static DeckCaracole get() {
        if (instance == null) {
            instance = new DeckCaracole();
        }
        return instance;
    }

    public static List<CarteCaracole> getDeck() {
        Logger.info("getDeck:");
        return deck;
    }

    public static void createDeck() throws CaracoleException {
        Logger.info("createDeck:");
        if (deck != null) {
            Logger.debug("erreur deck non null");
            throw new CaracoleException("un deck existe déjà !");
        } else {
            Logger.debug("deckGeneration:");
            deck = new ArrayList<>();
            for (Enseigne enseigne : Enseigne.values()) {
                for (Valeur valeur : Valeur.values()) {
                    CarteCaracole carte = new CarteCaracole();
                    carte.enseigne = enseigne;
                    carte.imageDos = null;
                    carte.imageFace = null;
                    carte.valeur = valeur;
                    carte.isVisible = false;
                    carte.emplacement = EmplacementCarte.PIOCHE;
                    //if point=0 then the card is a joker and it skip generating it in the deck
                    //TODO gérer la création des joker dans le deck
                    if (valeur.getPoint() == 0) {
                        continue;
                    }
                    deck.add(carte);
                    Logger.debug(carte.valeur.getNom() + " de " + carte.enseigne.getEnseigne() + " (" + carte.enseigne.getCouleur() + ")");
                }
            }
        }
    }

    public static void clearDeck() {
        Logger.info("clearDeck:");
        deck = null;
    }
}
