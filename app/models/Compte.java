package models;

import com.google.common.base.Strings;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Compte implements Serializable {
    @Id @GeneratedValue(generator="UUID")
    @GenericGenerator(name="UUID", strategy = "uuid")
    public String idCompte;
    @Column(unique = true, nullable = false)
    public String email;
    @Column(nullable = false)
    public String mdp;
    @Column(nullable = false)
    public String pseudo;
    public Boolean isValidated;

    @Override
    public String toString(){
        return "email : " + this.email + " (id : " + this.idCompte + ")";
    }
}
