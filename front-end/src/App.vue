
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
            <v-img src="https://www.flaticon.com/svg/vstatic/svg/758/758669.svg?token=exp=1611657060~hmac=c95a9b88ae37d132bc6381cc7fbdf0b5"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title class="font-weight-bold" style="margin: 15px">신싸피</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-spacer></v-spacer>
        <v-divider></v-divider>
        <v-spacer></v-spacer>

        <v-list dense>
          <v-list-item
            v-for="user in users"
            :key="user.menu"
            link
          >
            <v-list-item-content>
              <v-list-item-title>{{ user.menu }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>

          <v-list-item
            v-for="item in items"
            :key="item.title"
            link
          >
            <v-list-item-content>
              <v-list-item-title class="font-weight-bold">
                <v-icon color="pink">{{ item.icon }}</v-icon>
                {{ item.menu }}
              </v-list-item-title>
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
        users: [
          { menu: '회원가입'},
          { menu: '로그인'},
          { menu: '로그아웃'},
        ],
        items: [
          { menu: '홈', icon: 'mdi-home' },
          { menu: '코로나 정보', icon: 'mdi-comment-text-outline' },
        ],
      }
  }
};
</script>

<style scoped>
  #logo {
    max-height : 100px;
    max-width : 200px;
    margin : 0 auto;
  }

  .v-list-item {
    min-height: 60px !important;
  }

  .v-icon {
    margin-right: 10px;
  }

  .bar {
    margin: 10px;
  }


</style>