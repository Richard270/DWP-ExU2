<template>
  <b-container.fluid class="mb-3 ">
    <b-row>
      <b-col cols="12" md="12">
        <carousel :per-page="4" :auto-play="true" :pause="false" :interval="3000">
          <slide src="./img/portada.jpg" alt="Book cover" />
          <slide src="./img/portada.jpg" alt="Book cover" />
          <slide src="./img/portada.jpg" alt="Book cover" />
          <slide src="./img/portada.jpg" alt="Book cover" />
          <slide src="./img/portada.jpg" alt="Book cover" />
          <slide src="./img/portada.jpg" alt="Book cover" />
        </carousel>
      </b-col>
    </b-row>

    <div class="cuerpo">
      <b-row>
        <b-col cols="9" align-self="center">
          <h2>Libros</h2>
        </b-col>
        <b-col cols="3" align-self="center">
          <b-button class="mx-2" variant="dark" size="lg" @click="showAddBookModal = true" ref="addBookButton">
            <b-icon icon="plus-lg" aria-hidden="true"></b-icon> Añadir
          </b-button>
        </b-col>
      </b-row>

      <b-list-group>
        <b-list-group-item>
          <img src="./img/portada.jpg" alt="Book cover">
          <h5 class="mb-0">Título del libro</h5>
          <p class="mb-0">Autor del libro</p>
        </b-list-group-item>
      </b-list-group>
    </div>

  </b-container.fluid>

  <AddBookModal model="showAddBookModal" />
</template>