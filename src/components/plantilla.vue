<script>
</script>

<template>
  <div class="container-fluid">
      <b-row class="nav">
          <b-col cols="12">
            <carousel :per-page="1" :navigate-to="someLocalProperty" :mouse-drag="false">
                <slide>
                Slide 1 Content
                </slide>
                <slide>
                Slide 2 Content
                </slide>
            </carousel>
          <div class="button">
              <b-button class="mx-2" variant="dark" >Ordenar por autor</b-button>
              <b-button class="mx-2" variant="dark" >Ordenar por fecha</b-button>
              <b-button class="mx-2" variant="dark" >Mortrar si tiene imagen</b-button>
          </div>
          </b-col>
      </b-row>
  </div>
</template>

<style>
.button{
    display: flex;
    justify-content: center;
    margin-top: 15px;
}
</style>