package es.urjc.code.daw.models;


import java.io.Serializable;
import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;


/**
 * The persistent class for the user database table.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String userType;

    public User(String name, String password, String email, String userType) {
        this.name = name;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.email = email;
        this.userType = userType;
    }

    public User(String visitor) {
        this.userType = visitor;
    }


    public long getId() {
        return idUser;
    }

    public void setId(Integer id) {
        this.idUser = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpassword() {
        return password;
    }

    public void setPassword(String name) {
        this.password = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


}