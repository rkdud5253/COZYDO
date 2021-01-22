
<template>
  <v-app>
    <v-sheet
      height="100%"
      class="overflow-hidden"
      style="position: relative;"
    >

      <v-toolbar
        color="white"
        dark
        flat
      >

      <v-container >
        <v-toolbar-items>
            <v-icon 
              color="pink"
              dark
              @click.stop="drawer = !drawer"
              large
            >
              mdi-reorder-horizontal
            </v-icon>

            <v-img id="logo" src="../public/img/cozydo_logo.png"></v-img>

            <v-icon
              color="pink"
              large
            >
              mdi-account
            </v-icon>

        </v-toolbar-items>
            
      </v-container>

      </v-toolbar>


      <v-navigation-drawer
        v-model="drawer"
        absolute
        temporary
      >
        <v-list-item>
          <v-list-item-avatar>
            <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>이다운</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-divider></v-divider>

        <v-list dense>
          <v-list-item
            v-for="item in items"
            :key="item.title"
            link
          >
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
          <v-main class="main">
            <router-view />
          </v-main>
    </v-sheet>

  </v-app>
</template>

<style>
  #logo {
    max-height : 100px;
    max-width : 200px;
    margin : 0 auto;
  }
</style>

<script>
// import HelloWorld from "./components/HelloWorld";
import "./components/css/common.css";

export default {
  name: "App",

  components: {
    // HelloWorld,
    
  },

  data () {
      return {
        drawer: null,
        items: [
          { title: '회원가입', icon: 'mdi-forum' },
          { title: '로그인', icon: 'mdi-forum' },
          { title: '로그아웃', icon: 'mdi-forum' },
          { title: 'Home', icon: 'mdi-view-dashboard' },
          { title: '코로나 정보', icon: 'mdi-forum' },
        ],
      }
  }
};
</script>
