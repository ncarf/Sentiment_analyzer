<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid center">
        <a class="navbar-brand" href="#">Reddit en un stream</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0 mx-5">
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Dropdown
              </a>
              <ul class="dropdown-menu mx-5" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <a class="dropdown-item" href="#">Something else here</a>
                </li>
              </ul>
            </li>
          </ul>
          <form class="d-flex mx-5">
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
            />
            <button class="btn btn-outline-success" type="submit">
              Buscar
            </button>
          </form>
        </div>
      </div>
    </nav>

    <div v-for="post in posts" v-bind:key="post.redditId">
      <p>
      <div class="card center text-left" style="width: 40rem;">
        <div class="card-body">
          <h5 class="card-title"> <small>{{formatFlair(post.flairs)}}</small> <b>TITULO</b></h5>
          <h6 class="card-subtitle mb-2 text-muted"><b>id: </b>{{ post.redditId.toLowerCase()}}</h6>
          <h6 class="card-subtitle mb-2 text-muted"><b>autor: </b> {{ post.autor.toLowerCase()}}</h6>
          <p class="card-text">
            {{ post.texto }}
          </p>
          <hr class="hrCustom">
          <h5><small><b>Análisis lingüístico:</b></small></h5>
          <hr class="hrCustom">

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
      if(flairs === null){
        return "";
      }
      else{
        return '(' + flairs.toString() + ')';
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
a {
  color: #426cb9;
}
</style>
