
<template>
  <v-app>
    <v-sheet
      height="100%"
      class="overflow-hidden"
      style="position: relative;"
    >

      <v-toolbar
        class="bar"
        color="white"
        flat
      >
        <v-container >
          <v-toolbar-items>
              <v-img id="logo" src="../public/img/cozydo_logo.png"></v-img>
          </v-toolbar-items>
          <v-toolbar-items style="justify-content: center">
            <h6>일일확진자 <span style="color: #fc355d">451▲</span></h6>
          </v-toolbar-items>
        </v-container>
      </v-toolbar>

      <v-main class="main">
        <router-view />
      </v-main>

    </v-sheet>
    <BottomNav />
  </v-app>
</template>


<script>
// import HelloWorld from "./components/HelloWorld";
import "./components/css/common.css";
import BottomNav from "@/components/BottomNav.vue";

export default {
  name: "App",

  components: {
    BottomNav,
  },

  data () {
    return {

      }
  }
};
</script>

<style scoped>
  #logo {
    max-height : 100px;
    max-width : 200px;
    margin : 5px auto;
  }

  .bar {
    margin: 10px;
  }


</style>