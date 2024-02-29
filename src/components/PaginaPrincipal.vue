<script>
 export default {
      data:() => ({
        active: 0
      })
    }
    
</script>

<template>
    <b-container.fluid class="bv-example-row mb-3 ">
    <b-row>
      <b-col cols="12" md="12">
        <carousel :per-page="1" :navigate-to="someLocalProperty" :mouse-drag="false">
                <slide>
                Slide 1 Content
                </slide>
                <slide>
                Slide 2 Content
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
            <b-col cols="9" align-self="center">Libros</b-col>
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
</style>