package distribuidos.models;

public class Post_stats {

    private String redditId;
    private int upperCase;
    private int lowerCase;
    private int vowels;
    private int consonants;
    private int words;
    private int stopwords;
    private float polarity;

    public Post_stats() {
    }

    public Post_stats(String redditId, int upperCase, int lowerCase, int vowels, int consonants, int words, int stopwords, float polarity) {
        this.redditId = redditId;
        this.upperCase = upperCase;
        this.lowerCase = lowerCase;
        this.vowels = vowels;
        this.consonants = consonants;
        this.words = words;
        this.stopwords = stopwords;
        this.polarity = polarity;
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
}
