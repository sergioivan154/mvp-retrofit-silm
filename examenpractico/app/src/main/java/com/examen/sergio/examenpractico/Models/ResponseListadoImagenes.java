
package com.examen.sergio.examenpractico.Models;


import android.widget.ImageView;

public class ResponseListadoImagenes {

    private String id_f;
    private String title;
    private String description;
    private String upd;
    private String img;

    /**
     * No args constructor for use in serialization
     */
    public ResponseListadoImagenes() {
    }

    /**
     * @param title
     * @param description
     * @param id_f
     * @param img
     * @param upd
     */
    public ResponseListadoImagenes(String id_f, String title, String description, String upd, String img) {
        super();
        this.id_f = id_f;
        this.title = title;
        this.description = description;
        this.upd = upd;
        this.img = img;
    }

    public String getId_f() {
        return id_f;
    }

    public void setId_f(String id_f) {
        this.id_f = id_f;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpd() {
        return upd;
    }

    public void setUpd(String upd) {
        this.upd = upd;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
