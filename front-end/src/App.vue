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
            <v-img @click="goHome" id="logo" src="../public/img/cozydo_logo.png"></v-img>
          </v-toolbar-items>
          <CoronaTopNav />
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
import CoronaTopNav from "@/components/corona/CoronaTopNav.vue";

export default {
  name: "App",

  components: {
    BottomNav,
    CoronaTopNav,
  },

  data () {
    return {

      }
  },
  methods: {
    goHome() {
      this.$router.go(this.$router.push({name: 'Main'}))
    },
  }
};
</script>

<style scoped>
  #logo {
    max-height : 100px;
    max-width : 200px;
    margin : 5px auto;
    margin-bottom: auto;
  }

  .bar {
    margin: 10px;
  }


</style>