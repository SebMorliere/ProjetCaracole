import models.JoueurCaracole;
import models.exception.CaracoleException;
import services.JoueurService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        label:
        while (true) {
            System.out.println("Menu : ");
            System.out.println("[2] : ajouter un joueur");
            System.out.println("[3] : liste des joueurs");
            System.out.print("*** CHOIX >>>");

            String command = br.readLine();
            switch (command) {
                case "2":
                    if (JoueurService.isPlateauFull()) {
                        System.out.println("Plus de place disponible pour de nouveaux joueurs !");
                        break;
                    }
                    System.out.println("> ajout d'un joueur : ");
                    System.out.println("entrez le pseudo : ");
                    command = br.readLine();
                    try {
                        JoueurCaracole joueur1 = JoueurService.get().addPlayer(command);
                        System.out.println("joueur ajouté à la table ! (" + joueur1.pseudo + ")");
                    } catch (CaracoleException e) {
                        System.out.println(e);
                    }
                    break;
                case "3":
                    System.out.println("> liste des joueur à la table : ");
                    List<JoueurCaracole> joueurs = JoueurService.get().findPlayers();
                    for (JoueurCaracole joueur : joueurs) {
                        System.out.println("Emplacement " + joueur.emplacement.getEmplacement() + " --> " + joueur.pseudo);
                    }
                    break;
                //TODO utiliser les nouvelles fonctions
            }
        }

    }
}
