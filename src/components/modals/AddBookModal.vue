<template>
  <b-modal id="add-book-modal" title="Agregar libro" v-model="showAddBookModal">
    <b-form @submit="onSubmit">
      <b-form-group label="Nombre del libro">
        <b-form-input model="book.name" required></b-form-input>
      </b-form-group>
      <b-form-group label="Autor">
        <b-form-input model="book.author" required></b-form-input>
      </b-form-group>
      <b-form-group label="Fecha de publicación">
        <b-form-datepicker model="book.publicationDate" required></b-form-datepicker>
      </b-form-group>
      <b-button type="submit" variant="primary">Agregar</b-button>
      <b-button variant="outline-secondary" @click="showAddBookModal = false">Cancelar</b-button>
    </b-form>
  </b-modal>
</template>

<script>
export default {
  data() {
    return {
      showAddBookModal: false,
      book: {
        name: '',
        author: '',
        publicationDate: null,
      },
    };
  },
  methods: {
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
  },
};
</script>
