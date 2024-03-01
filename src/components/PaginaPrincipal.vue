<script>
 export default {
    data() {
    return {
      books: [],
      filteredBooks: [],
      filter: 'all',
      i: 0,
      row: null,
    };
    },
    mounted() {
    this.fetchBooks();
  },
  methods: {
    async fetchBooks() {
      const response = await fetch('/');
      const data = await response.json();
      this.books = data;
      this.filteredBooks = this.books;
      this.generateRows();
    },
    filterBooks(filter) {
      this.filter = filter;
      if (filter === 'all') {
        this.filteredBooks = this.books;
      } else {
        this.filteredBooks = this.books.filter(book => book[filter]);
      }
    },
    generateRows() {
      this.i = 0;
      this.row = null;
      const listGroup = this.$refs.listGroup;

      for (const book of this.books) {
        this.i++;

        if (this.i % 3 === 1) {
          this.row = document.createElement('b-row');
          listGroup.appendChild(this.row);
        }

        const col = document.createElement('b-col');
        col.setAttribute('cols', '4');
        col.appendChild(this.createCard(book));
        this.row.appendChild(col);
      }
    },
    createCard(book) {
      const card = document.createElement('b-card');
      const img = document.createElement('img');
      const h5 = document.createElement('h5');
      const p = document.createElement('p');

      img.setAttribute('src', book.image);
      img.setAttribute('alt', 'Book cover');
      img.classList.add('portada');

      h5.textContent = book.title;
      h5.classList.add('mb-0');

      p.textContent = book.author;
      p.classList.add('mb-0');

      card.appendChild(img);
      card.appendChild(h5);
      card.appendChild(p);

      return card;
    },
  },
  refs: {
    listGroup: 'listGroup',
  },
};
</script>

<template>
    <b-container.fluid class="bv-example-row mb-3 ">
    <b-row>
      <b-col cols="12" md="12">
        <carousel :per-page="4" :auto-play="true" :pause="false" :interval="3000">
                <slide>
                    <img src="./img/portada.jpg" alt="Book cover">
                </slide>
                <slide>
                    <img src="./img/portada.jpg" alt="Book cover">
                </slide>
                <slide>
                    <img src="./img/portada.jpg" alt="Book cover">
                </slide>
                <slide>
                    <img src="./img/portada.jpg" alt="Book cover">
                </slide>
                <slide>
                    <img src="./img/portada.jpg" alt="Book cover">
                </slide>
                <slide>
                    <img src="./img/portada.jpg" alt="Book cover">
                </slide>
            </carousel>
      </b-col>
      <b-col cols="12" md="12">
        <div class="button">
              <b-button class="mx-2" variant="dark" >Ordenar por autor</b-button>
              <b-button class="mx-2" variant="dark" >Ordenar por fecha</b-button>
              <b-button class="mx-2" variant="dark" >Mortrar si tiene imagen</b-button>
          </div>
      </b-col>
    </b-row>
    <div class="cuerpo">
        <b-row>
            <b-col cols="9" align-self="center">
                <h2>Libros</h2>
            </b-col>
            <b-col cols="9" align-self="center">
                <b-list-group>
                </b-list-group>
            </b-col>
            <b-col cols="3" align-self="center">
                <b-button class="mx-2" variant="dark" size="lg">
                <b-icon icon="plus-lg" aria-hidden="true"></b-icon> Añadir
            </b-button>
            <b-button class="mx-2" variant="dark" size="lg">
                <b-icon icon="pencil-square" aria-hidden="true"></b-icon> Editar
            </b-button>
            <b-button class="mx-2" variant="dark" size="lg">
                <b-icon icon="trash" aria-hidden="true"></b-icon> Eliminar
            </b-button>
            </b-col>
        </b-row>
    </div>
  </b-container.fluid>
</template>

<style>
.button{
    display: flex;
    justify-content: center;
    margin-top: 15px;
}
.cuerpo{
    margin-top: 15px;
}
#book-carousel {
  width: 100%;
  height: 300px;
}
#book-carousel img {
  width: 100%;
  height: 100%;
}
img {
  width: 200px;
  height: 250px;
}
.portada{
    width: 100px;
    height: 150px;
}
</style>