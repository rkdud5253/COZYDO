<template>
  <v-app>

    <div class="search">
      <v-icon class="goback"
        color="pink"
        large
        @click="goBack"
        >
        mdi-chevron-left
      </v-icon>
      <v-btn color="#FF8199" class="searchButton" dark href="/searchResultList">
        <v-icon>mdi-format-list-bulleted-square</v-icon>
        리스트
      </v-btn>
      <v-text-field class="searchText"
            v-model="word"
            solo
            label="장소검색"
            clearable
      ></v-text-field>    
    </div>
    
    <div class="checkbox">
      <v-checkbox class="pr-6" label="내위치중심" value="mylocation" color="#88C8FF"></v-checkbox>
      <v-checkbox class="pr-6" label="지도중심" value="map" color="#88C8FF"></v-checkbox>
    </div>

   <SearchMap />
    
  </v-app>

  
</template>
<style scoped>
.search {
  width: 95%;
  margin: 10% auto;
  margin-bottom: -2%;
  display: flex;
}

.goback {
   margin-bottom : 5%;
}

.searchButton {
  float : left;
  width: 20%;
  height: 45px !important;
  box-sizing: border-box;
  border: 0 auto; 
  margin-left: 3%;
  margin-right : 7%;
  margin-top : 0.5%;
}

.searchText {
  float : left;
  width: 30%;
  box-sizing: border-box;
  margin-right: 3% !important;
}

.checkbox {
  width: 90%;
  display: flex;
  margin-left:5%;
}


</style>

<script>
import SearchMap from "@/components/SearchMap.vue";
export default {
  name: 'SearchResultMap',
  components: {
      SearchMap,
  },
  data: function () {
  return {
    items: [
      
    ],
    word : '카페',
    }
  },
  methods: {
    goBack () {
        this.$router.go(-1)
      },
  },
}
</script>