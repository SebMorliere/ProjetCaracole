package controllers;

import models.CarteCaracole;
import models.Compte;
import models.exception.CaracoleException;
import play.Logger;
import play.mvc.Controller;
import services.CompteService;
import services.DeckCaracole;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    public static void index() {
        Logger.info("index:");
        render();
    }

    public static void generateDeck() {
        Logger.info("generateDeck:");
        try {
            DeckCaracole.get().clearDeck();
            DeckCaracole.get().createDeck();
            flash("success", "deck créé !");
        } catch (CaracoleException e) {
            Logger.error(String.valueOf(e));
            flash("error", String.valueOf(e.getMessage()));
        } finally {
            renderTemplate("Application/index.html");
        }
    }

    public static void getDeck() {
        Logger.info("getDeck:");
        List<CarteCaracole> deck = new ArrayList<>();
        deck = DeckCaracole.get().getDeck();
        render(deck);
    }

    public static void clearDeck() {
        Logger.info("clearDeck:");
        DeckCaracole.get().clearDeck();
        flash("success", "deck effacé !");
        renderTemplate("Application/index.html");
    }

    public static void newCompte() {
        Logger.info("newCompte:");
        render();
    }

    public static void createCompte(String pseudo, String email, String mdp) {
        Logger.info("createCompte:");
        try {
            CompteService.get().create(email, mdp, pseudo);
            flash("success", "compte créé !");
            renderTemplate("Application/index.html");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            flash("error", String.valueOf(e.getMessage()));
            renderTemplate("Application/index.html");
        }
    }

    public static void findComptes() {
        Logger.info("findComptes:");
        List<Compte> comptes = CompteService.get().findComptes();
    }

}