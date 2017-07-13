
package com.examen.sergio.examenpractico.Models;

// se generó con http://www.jsonschema2pojo.org
public class Dto {

    private int id_u;
    private String user_name;
    private String user_pass;
    private String upd;
    private String mail;

    private int id;
    private String user;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Dto() {
    }

    /**
     *  endpoint https://canditossoft.000webhostapp.com/services/insertuser.php?username=admin&userpass=administra&mail=mail@mail.com
     * @param mail
     * @param id_u
     * @param user_name
     * @param upd
     * @param user_pass
     */
    public Dto(int id_u, String user_name, String user_pass, String upd, String mail) {
        super();
        this.id_u = id_u;
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.upd = upd;
        this.mail = mail;
    }

    /**
     *  https://canditossoft.000webhostapp.com/services/searchuser.php?user_name=admin&user_pass=administra
     * @param mail
     * @param id
     * @param user
     * @param mail
     */
    public Dto(int id, String user, String mail) {
        super();
        this.id = id;
        this.user = user;
        this.mail = mail;
    }



    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUpd() {
        return upd;
    }

    public void setUpd(String upd) {
        this.upd = upd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    //Estos campos son del endpoint https://canditossoft.000webhostapp.com/services/searchfeed.php
    // se ha notado una ligera variacion en la variable ya que id y user tambien existen en el endpoint https://canditossoft.000webhostapp.com/services/insertuser.php?username=admin&userpass=administra&mail=mail@mail.com
    // pero con distinto nombre

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
