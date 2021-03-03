package distribuidos.models;

import java.util.ArrayList;

public class Stored_posts {

    private String redditId;
    private String titulo;
    private String texto;
    private String autor;
    private int puntaje;
    private ArrayList<String> flairs;

    public Stored_posts() {
    }

    public Stored_posts(String redditId, String titulo, String texto, String autor, int puntaje, ArrayList<String> flairs) {
        this.redditId = redditId;
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
        this.puntaje = puntaje;
        this.flairs = flairs;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRedditId() {
        return redditId;
    }

    public void setRedditId(String redditId) {
        this.redditId = redditId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public ArrayList<String> getFlairs() {
        return flairs;
    }

    public void setFlairs(ArrayList<String> flairs) {
        this.flairs = flairs;
    }
}
