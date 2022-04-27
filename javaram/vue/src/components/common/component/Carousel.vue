<template>

  <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">

    <div id="carousel_tab">
        <h3 id="carousel_title">Carousel</h3>
    </div>

    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUbsyOFVz59lwvPN9oZGEVkIBHuT3zGdzPCQ&usqp=CAU"
          class="d-block" alt="...">
      </div>
      <div class="carousel-item">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRX1rewOaGvUMmdNigskYGmno3crPsteW8swA&usqp=CAU"
          class="d-block" alt="...">
      </div>
      <div class="carousel-item">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRssugE2MNwt_MpSzpR1WzVhVU_wNvuubKcfA&usqp=CAU"
          class="d-block" alt="...">
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>

  </div>

</template>

<script>
export default {
  name: "carouselComponent"
}
</script>

<style scoped>
  .carousel-inner img{
    width: auto; 
    height: auto;
    max-width: 300px;
    max-height: 200px;
  }

  #carousel_tab {
    /* display: flex; */
    flex-direction: row;
    flex:1;
    border-left-style: solid;
    border-left-width: 5px;
    border-left-color: #00C9BE;
  }

  #carousel_title {
    margin-left: 15px;
    align-self: center;
    flex: 6;
  }

</style>