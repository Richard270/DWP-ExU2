<script>
 export default {
    data() {
    return {
      books: [],
      filteredBooks: [],
      filter: 'all',
      i: 0,
      row: null,
      activeBook: null,
      bookToUpdate: null,
      showUpdateBookModal: false,
      showAddBookModal: false,
      book: {
        name: '',
        author: '',
        publicationDate: null,
      },
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
    onDrag(book) {
      this.activeBook = book; 
    },
    allowDrop(event) {
        event.preventDefault();
        if (event.target.classList.contains('dropzone')) {
            event.dataTransfer.dropEffect = 'move';
        } else {
            event.dataTransfer.dropEffect = 'none';
        }
    },
    dropBook(book, event) {
        event.preventDefault();
        if (event.target.classList.contains('dropzone')) {
            this.showUpdateBookModal = true; 
            this.bookToUpdate = book;
        } else {
            this.deleteBook(book);
        }
    },
    async updateBook() {
  try {
    const response = await fetch('/' + this.bookToUpdate.id, {
      method: 'PUT',
      body: JSON.stringify(this.bookToUpdate),
    });
    if (response.ok) {
      const updatedBook = await response.json();
      this.books = this.books.map(book => book.id === updatedBook.id ? updatedBook : book);
      this.filteredBooks = this.filteredBooks.map(book => book.id === updatedBook.id ? updatedBook : book);
      this.$bvToast.toast('Libro actualizado exitosamente', {
        type: 'success',
      });
      this.closeUpdateModal();
    } else {
      this.$bvToast.toast('Error al actualizar el libro', {
        type: 'danger',
      });
    }
  } catch (error) {
    console.error('Error updating book:', error);
    this.$bvToast.toast('Error de conexión', {
      type: 'danger',
    });
  }
},

async deleteBook(book) {
  try {
    const response = await fetch('/' + book.id, { method: 'DELETE' });
    if (response.ok) {
      this.books = this.books.filter(b => b.id !== book.id);
      this.filteredBooks = this.filteredBooks.filter(b => b.id !== book.id);
      this.$bvToast.toast('Libro eliminado exitosamente', {
        type: 'success',
      });
    } else {
      this.$bvToast.toast('Error al eliminar el libro', {
        type: 'danger',
      });
    }
  } catch (error) {
    console.error('Error deleting book:', error);
    this.$bvToast.toast('Error de conexión', {
      type: 'danger',
    });
  }
},
onSubmit(evt) {
      evt.preventDefault();
      axios.post('/package.json', this.book)
        .then(() => {
          this.showAddBookModal = false;
          this.$bvToast.show('Libro agregado exitosamente');
        })
        .catch(error => {
          this.$bvToast.show('Error al agregar el libro', {
            type: 'danger',
          });
        });
        
      this.book = {
        name: '',
        author: '',
        publicationDate: null,
      };
    },
    openCreateModal() {
        this.bookToCreate = {
            title: '',
            author: '',
        };
    this.$bvModal.show('createBookModal');
    }

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
                <b-button class="mx-2" variant="dark" size="lg" @click="openCreateModal">
                <b-icon icon="plus-lg" aria-hidden="true"></b-icon> Añadir
            </b-button>
            <b-button class="mx-2" variant="dark" size="lg" @click="openUpdateModal(book)">
                <b-icon icon="pencil-square" aria-hidden="true"></b-icon> Actualizar
            </b-button>
            <b-button class="mx-2" variant="dark" size="lg" @click="deleteBook(book)">
                <b-icon icon="trash" aria-hidden="true"></b-icon> Eliminar
            </b-button>
            </b-col>
        </b-row>
        <div id="updateBookModal" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Actualizar libro</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form @submit.prevent="updateBook">
                    <div class="form-group">
                        <label for="title">Título</label>
                        <input type="text" v-model="bookToUpdate.title" id="title" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="author">Autor</label>
                        <input type="text" v-model="bookToUpdate.author" id="author" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                    </form>
                </div>
                </div>
            </div>
        </div>
        <div id="createBookModal" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Crear nuevo libro</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form @submit.prevent="createBook">
                    <div class="form-group">
                        <label for="title">Título</label>
                        <input type="text" v-model="bookToCreate.title" id="title" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="author">Autor</label>
                        <input type="text" v-model="bookToCreate.author" id="author" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Crear</button>
                    </form>
                </div>
                </div>
            </div>
        </div>
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