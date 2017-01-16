import models.Compte;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Logger;
import play.test.UnitTest;
import services.CompteService;
import services.HibernateUtils;

import java.util.ArrayList;

public class CompteServiceTest extends UnitTest {

    @Before
    public void avantTest(){
        Logger.debug("beforeClass:");
        CompteService.get().clearTestDB();
    }
    @After
    public void apresTest(){
        Logger.debug("afterTest:");
        CompteService.get().clearTestDB();
    }

    @BeforeClass
    public static void avantClassTest() {
        Logger.debug("beforeClass:");
        HibernateUtils.HibernateTest();
    }

    private String email = "toto@tata.fr";
    private String mdp = "toto";
    private String email1 = "toto@tata.fr";
    private String mdp1 = "toto";
    private String email2 = "titi@tata.fr";
    private String mdp2 = "titi";
    private String emailWrong = "tototata.fr";
    private String emailNull = null;
    private String mdpNull = null;


    @Test
    public void create_allValid(){
        Logger.debug("create_allValid:");
        try {
            CompteService.get().create(email, mdp);
        } catch (Exception e) {
            fail("erreur lors de la creation du compte: " + e.getMessage());
        }
        Compte compte = CompteService.get().getCompteByEmail(email);
        assertTrue(compte.mdp.equals(mdp));
        assertNotNull(compte.idCompte);
    }

    @Test
    public void create_emailWrong(){
        Logger.debug("create_emailWrong: ");
        try {
            CompteService.get().create(emailWrong, mdp);
            fail("compte créé malgré email invalide");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "email non valide");
        }
    }

    @Test
    public void create_emailNull(){
        Logger.debug("create_emailNull: ");
        try {
            CompteService.get().create(emailNull, mdp);
            fail("compte créé malgré email nul");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "email non valide");
        }
    }

    @Test
    public void create_emailNonUnique() {
        Logger.debug("create_emailNonUnique");
        try {
            CompteService.get().create(email, mdp1);
            CompteService.get().create(email1, mdp2);
            fail("comptes créés malgré email déjà présent en base");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "email déjà utiilisé");
        }
    }

    @Test
    public void create_mdpNull(){
        Logger.debug("create_mdpNull: ");
        try {
            CompteService.get().create(email, mdpNull);
            fail("compte créé malgré mdp nul");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "le mot de passe ne peut être nul");
        }

    }

    @Test
    public void findComptes_allValid(){
        Logger.debug("findComptes_allValid:");
        try {
            CompteService.get().create(email1, mdp1);
            CompteService.get().create(email2, mdp2);
        } catch (Exception e) {
            fail("erreur lors de la création du compte: " + e.getMessage());
        }
        ArrayList<Compte> comptes = (ArrayList<Compte>) CompteService.get().findComptes();
        assertTrue( comptes.size() > 1 );
        comptes = (ArrayList<Compte>) CompteService.get().findComptes(true);
        assertTrue( comptes.size() > 1 );
    }

    @Test
    public void findComptes_emptyDB(){
        Logger.debug("findComptes_emptyDB:");
        CompteService.get().clearTestDB();
        ArrayList<Compte> comptes = (ArrayList<Compte>) CompteService.get().findComptes();
        assertTrue( comptes.size() < 1 );
    }

    @Test
    public void getCompteById_allValid(){
        Logger.debug("getCompteById_allValid:");
        try {
            CompteService.get().create(email1, mdp1);
            CompteService.get().create(email2, mdp2);
        } catch (Exception e) {
            fail("erreur lors de la création du compte: " + e.getMessage());
        }
        String idCompte = CompteService.get().getCompteByEmail(email1).idCompte;
        Compte compte = CompteService.get().getCompteById(idCompte);
        assertTrue(compte.email.equals(email1));
    }

    @Test
    public void getCompteById_idWrong(){
        Logger.debug("getCompteById_idWrong:");
        try {
            CompteService.get().create(email1, mdp1);
            CompteService.get().create(email2, mdp2);
        } catch (Exception e) {
            fail("erreur lors de la création du compte: " + e.getMessage());
        }
        Compte compte = CompteService.get().getCompteById("abc");
        assertTrue(compte == null);
    }

    @Test
    public void getCompteById_emptyDB(){
        Logger.debug("getCompteById_emptyDB");
        CompteService.get().clearTestDB();
        Compte compte = CompteService.get().getCompteById("abc");
        assertTrue(compte == null);
    }

    @Test
    public void modifyCompte_allValid(){
        Logger.debug("modifyCompte_allValid:");
        try {
            CompteService.get().create(email, mdp);
        } catch (Exception e) {
            fail("erreur lors de la création du compte: " + e.getMessage());
        }
        Compte compte = CompteService.get().getCompteByEmail(email);
        compte.email = email2;
        compte.pseudo = "tete";
        compte.mdp = "123";
        compte.isValidated = !compte.isValidated;
        try {
            CompteService.get().modifyCompte(compte);
        } catch (Exception e) {
            fail("erreur lors de la création du compte: " + e.getMessage());
        }
        Compte compte1 = CompteService.get().getCompteById(compte.idCompte);
        assertEquals(compte1.email, compte.email);
        assertEquals(compte1.pseudo, compte.pseudo);
        assertEquals(compte1.mdp, compte.mdp);
        assertEquals(compte1.isValidated, compte.isValidated);
    }

    @Test
    public void modifyCompte_emailWrong(){
        Logger.debug("modifyCompte_emailWrong:");
        try {
            CompteService.get().create(email, mdp);
        } catch (Exception e) {
            fail("erreur lors de la création du compte: " + e.getMessage());
        }
        Compte compte = CompteService.get().getCompteByEmail(email);
        compte.email = emailWrong;
        compte.pseudo = "tete";
        compte.mdp = "123";
        compte.isValidated = !compte.isValidated;
        try {
            CompteService.get().modifyCompte(compte);
            fail("compte modifié avec email invalide");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "email non valide ou déjà utilisé");
        }
    }

    @Test
    public void modifyCompte_EmailNull(){
        Logger.debug("modifyCompte_EmailNull:");
        try {
            CompteService.get().create(email, mdp);
        } catch (Exception e) {
            fail("erreur lors de la creation du compte: " + e.getMessage());
        }
        Compte compte = CompteService.get().getCompteByEmail(email);
        compte.email = emailNull;
        compte.pseudo = "tete";
        compte.mdp = "123";
        compte.isValidated = !compte.isValidated;
        try {
            CompteService.get().modifyCompte(compte);
            fail("compte modifié avec email nul");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "email non valide ou déjà utilisé");
        }
    }

    @Test
    public void modifyCompte_EmailNonUnique(){
        Logger.debug("modifyCompte_EmailNonUnique:");
        try {
            CompteService.get().create(email, mdp);
            CompteService.get().create(email2, mdp2);
        } catch (Exception e) {
            fail("erreur lors de la creation du compte: " + e.getMessage());
        }
        Compte compte = CompteService.get().getCompteByEmail(email);
        compte.email = email2;
        compte.pseudo = "tete";
        compte.mdp = "123";
        compte.isValidated = !compte.isValidated;
        try {
            CompteService.get().modifyCompte(compte);
            fail("compte modifié avec email non unique");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "email non valide ou déjà utilisé");
        }
    }

    @Test
    public void modifyCompte_mdpNull(){
        Logger.debug("modifyCompte_EmailNull:");
        try {
            CompteService.get().create(email, mdp);
        } catch (Exception e) {
            fail("erreur lors de la creation du compte: " + e.getMessage());
        }
        Compte compte = CompteService.get().getCompteByEmail(email);
        compte.email = email2;
        compte.pseudo = "tete";
        compte.mdp = mdpNull;
        compte.isValidated = !compte.isValidated;
        try {
            CompteService.get().modifyCompte(compte);
            fail("compte modifié avec email invalide");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "le mot de passe ne peut être nul");
        }
    }

    @Test
    public void modifyCompte_pseudoNull(){
        Logger.debug("modifyCompte_EmailNull:");
        try {
            CompteService.get().create(email, mdp);
        } catch (Exception e) {
            fail("erreur lors de la creation du compte: " + e.getMessage());
        }
        Compte compte = CompteService.get().getCompteByEmail(email);
        compte.email = email2;
        compte.pseudo = null;
        compte.mdp = mdpNull;
        compte.isValidated = !compte.isValidated;
        try {
            CompteService.get().modifyCompte(compte);
            fail("compte modifié avec email invalide");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "le pseudo ne peut être nul");
        }
    }

}
