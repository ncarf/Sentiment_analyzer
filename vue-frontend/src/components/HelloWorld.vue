<template>
  <div>
    <nav class="navbar navbar-expand-lg navBar">
      <div class="container-fluid">
        <div style="text-align: left">
          <img src="../assets/redditLogo.png" height="40" />
        </div>
      </div>
    </nav>

    <div v-for="post in posts" v-bind:key="post.redditId">
      <p></p>
      <div class="card center text-left customCard" style="width: 40rem;">
        <div class="card-body">
          <h5 class="card-title">
            <small>{{ formatFlair(post.flairs) }}</small>
            <b>{{ post.titulo }}</b>
          </h5>
          <h6 class="card-subtitle mb-2 text-muted">
            <b>id: </b>{{ post.redditId.toLowerCase() }}
          </h6>
          <h6 class="card-subtitle mb-2 text-muted">
            <b>autor: </b> {{ post.autor.toLowerCase() }}
          </h6>
          <p class="card-text">
            {{ post.texto }}
          </p>
          <hr class="hrCustom" />
          <h5>
            <small><b>Análisis lingüístico:</b></small>
          </h5>
          <h6><small>Palabras: {{ post.words }}</small></h6>
          <h6><small>Stopwords: {{ post.stopwords }}</small></h6>
          <h6><small>Mayúsculas: {{ post.upperCase }}</small></h6>
          <h6><small>Minúsculas: {{ post.lowerCase }}</small></h6>
          <h6><small>Vocales: {{ post.vowels }}</small></h6>
          <h6><small>Consonantes: {{ post.consonants }}</small></h6>
     
          <hr class="hrCustom" />

          <h5>
            <small><b>Análisis de sentimiento:</b></small>
            <h6><small>Polaridad: {{ post.polarity }}</small></h6>
          </h5>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    posts: {},
  }),
  methods: {
    formatFlair(flairs) {
      if (flairs === null) {
        return "";
      } else {
        return "(" + flairs.toString() + ")";
      }
    },
    async getPostsAll() {
      axios.get("http://0.0.0.0:8080/posts/all").then((response) => {
        this.posts = response.data;
        this.posts = Array.from(new Set(this.posts.map((a) => a.redditId))).map(
          (redditId) => {
            return this.posts.find((a) => a.redditId === redditId);
          }
        );
        console.log(this.posts);
        /* redditId: "";
        upperCase: -1;
        lowerCase: -1;
        vowels: -1;
        consonants: -1;
        words: -1;
        stopwords: -1;
        polarity: -2;
        texto: "";
        autor: "";
        puntaje: -1;
        flairs: []; */
      });
    },
  },
  created() {
    this.getPostsAll();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.center {
  margin: auto;
  width: 50%;
}
.text-left {
  text-align: left;
}
.hrCustom {
  border: 1px solid lightgrey;
}
.customCard {
  background-color: #151516;
  color: #c4c7c8;
}
.navBar {
  background-color: #151516;
  color: #c4c7c8;
}
.enStream {
  position: relative;
  top: 4px;
  font-weight: bold;
}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
</style>
