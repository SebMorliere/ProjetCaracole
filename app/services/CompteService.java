package services;

import com.google.common.base.Strings;
import models.Compte;
import models.validators.EmailValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

public class CompteService {

    /**
     * Singleton
     */
    private static CompteService instance;

    /**
     * Constructeur privé
     */
    private CompteService() {
    }

    /**
     * Seule méthode pour récupérer une instance (toujours la même)
     *
     * @return toujours la même instance
     */
    public static CompteService get() {
        if (instance == null) {
            instance = new CompteService();
        }
        return instance;
    }

    public static void create(String email, String mdp) throws Exception {
        create(email, mdp, email);
    }

    public static void create(String email, String mdp, String pseudo) throws Exception {
        Logger.info("create: email: {%s}", email);
        if(EmailValidator.validate(email) && !Strings.isNullOrEmpty(mdp)) {
            Logger.debug("getCompteByEmail test: ");
            if(isEmailUnique(email)) {
                Compte compte = new Compte();
                compte.email = email;
                compte.mdp = mdp;
                if (pseudo == null) {
                    compte.pseudo = email;
                } else {
                    compte.pseudo = pseudo;
                }
                compte.isValidated = true;

                Session session = HibernateUtils.getSession();
                Transaction tx = null;
                try {
                    Logger.debug("try session:");
                    tx = session.beginTransaction();
                    session.save(compte);
                    tx.commit();
                } catch (HibernateException e) {
                    if (tx != null) tx.rollback();
                    Logger.error("sessionError: ", e);
                    throw e;
                } finally {
                    session.close();
                }
            } else {
                Logger.debug("email déjà utilisé: (%s)", email);
                throw new Exception("email déjà utiilisé");
            }

        } else {
            if (Strings.isNullOrEmpty(mdp)) {
                throw new Exception ("le mot de passe ne peut être nul");
            } else {
                throw new Exception("email non valide"); }
            }
    }

    public static List<Compte> findComptes(){
        Logger.info("findComptes: {}");

        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("FROM Compte");
        List<Compte> comptes = query.list();
        session.close();

        return comptes;
    }

    public static List<Compte> findComptes(boolean isValidated) {
        Logger.info("findComptes: {%s}", isValidated);
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("FROM Compte WHERE isValidated =:isValidated");
        query.setParameter("isValidated", isValidated);
        List<Compte> comptes = query.list();
        session.close();
        return comptes;
    }

    public static Compte getCompteById(String idCompte){
        Logger.info("getCompteById: {%s}", idCompte);
        Session session = HibernateUtils.getSession();
        Compte compte = (Compte) session.get(Compte.class, idCompte);
        session.close();
        return compte;
    }

    public static Compte getCompteByEmail(String email) {
        Logger.info("getCompteByEmail: {%s}", email);
        Session session = HibernateUtils.getSession();
        Query query= session.createQuery("FROM Compte WHERE email =:email");
        query.setParameter("email", email);
        Compte compte = (Compte) query.uniqueResult();
        session.close();
        return compte;
    }

    public static void modifyCompte(Compte compte) throws Exception {
        Logger.info("modifyCompte: {%s}", compte.toString());
        Compte compte1 = CompteService.get().getCompteById(compte.idCompte);
        if (compte1.email != compte.email) {
            if(EmailValidator.validate(compte.email) && isEmailUnique(compte.email)) {
                compte1.email = compte.email;
            } else {
                throw new Exception("email non valide ou déjà utilisé");
            }
        }

        if (compte1.pseudo != compte.pseudo) {
            if(!Strings.isNullOrEmpty(compte.pseudo)) {
                compte1.pseudo = compte.pseudo;
            } else {
                throw new Exception("le pseudo ne peut être nul");
            }
        }

        if (compte1.mdp != compte.mdp) {
            if(!Strings.isNullOrEmpty(compte.mdp)) {
                compte1.mdp = compte.mdp;
            } else {
                throw new  Exception("le mot de passe ne peut être nul");
            }
        }

        if (compte1.isValidated != compte.isValidated) {
            compte1.isValidated = compte.isValidated;
        }

        Session session = HibernateUtils.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(compte1);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            Logger.error("sessionError: ", e);
            throw e;
        } finally {
            session.close();
        }
    }

    private static boolean isEmailUnique(String email) {
        Logger.info("isEmailUnique: {%s}", email);
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("FROM Compte WHERE email =:email");
        query.setParameter("email", email);
        ArrayList<Compte> comptes = (ArrayList<Compte>) query.list();
        session.close();
        if (comptes.size() < 1 ) return true;
        return false;
    }

    public static void clearTestDB() {
        Logger.info("clearTestDB:");
        HibernateUtils.HibernateTest();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE Compte");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
