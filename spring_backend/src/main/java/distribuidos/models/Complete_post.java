package distribuidos.models;

import java.util.ArrayList;
import java.util.Optional;

public class Complete_post {

    private String redditId;
    private int upperCase;
    private int lowerCase;
    private int vowels;
    private int consonants;
    private int words;
    private int stopwords;
    private float polarity;

    private String titulo;
    private String texto;
    private String autor;
    private int puntaje;
    private ArrayList<String> flairs;



    public Complete_post() {
    }

    public Complete_post(String redditId, int upperCase, int lowerCase, int vowels, int consonants, int words, int stopwords, float polarity, String titulo, String texto, String autor, int puntaje, ArrayList<String> flairs) {
        this.redditId = redditId;
        this.upperCase = upperCase;
        this.lowerCase = lowerCase;
        this.vowels = vowels;
        this.consonants = consonants;
        this.words = words;
        this.stopwords = stopwords;
        this.polarity = polarity;
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
        this.puntaje = puntaje;
        this.flairs = flairs;
    }

    public void build(Post_stats postgres, Stored_posts mongo)
    {
        this.redditId = mongo.getRedditId();
        this.titulo = mongo.getTitulo();
        this.texto = mongo.getTexto();
        this.autor = mongo.getAutor();
        this.puntaje = mongo.getPuntaje();
        this.flairs = mongo.getFlairs();

        this.upperCase = postgres.getUpperCase();
        this.lowerCase = postgres.getLowerCase();
        this.vowels = postgres.getVowels();
        this.consonants = postgres.getConsonants();
        this.words = postgres.getWords();
        this.stopwords = postgres.getStopwords();
        this.polarity = postgres.getPolarity();
    }

    public String getRedditId() {
        return redditId;
    }

    public void setRedditId(String redditId) {
        this.redditId = redditId;
    }

    public int getUpperCase() {
        return upperCase;
    }

    public void setUpperCase(int upperCase) {
        this.upperCase = upperCase;
    }

    public int getLowerCase() {
        return lowerCase;
    }

    public void setLowerCase(int lowerCase) {
        this.lowerCase = lowerCase;
    }

    public int getVowels() {
        return vowels;
    }

    public void setVowels(int vowels) {
        this.vowels = vowels;
    }

    public int getConsonants() {
        return consonants;
    }

    public void setConsonants(int consonants) {
        this.consonants = consonants;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public int getStopwords() {
        return stopwords;
    }

    public void setStopwords(int stopwords) {
        this.stopwords = stopwords;
    }

    public float getPolarity() {
        return polarity;
    }

    public void setPolarity(float polarity) {
        this.polarity = polarity;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
