package com.example.student_community.ui.home;

public class Posts {
    private int post_id;
    private String baslik_id;
    private String konum_id;
    private String kisaaciklama_id;

    public Posts() {
    }

    public Posts(int post_id, String baslik_id, String konum_id, String kisaaciklama_id) {
        this.post_id = post_id;
        this.baslik_id = baslik_id;
        this.konum_id = konum_id;
        this.kisaaciklama_id = kisaaciklama_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getBaslik_id() {
        return baslik_id;
    }

    public void setBaslik_id(String baslik_id) {
        this.baslik_id = baslik_id;
    }

    public String getKonum_id() {
        return konum_id;
    }

    public void setKonum_id(String konum_id) {
        this.konum_id = konum_id;
    }

    public String getKisaaciklama_id() {
        return kisaaciklama_id;
    }

    public void setKisaaciklama_id(String kisaaciklama_id) {
        this.kisaaciklama_id = kisaaciklama_id;
    }
}
