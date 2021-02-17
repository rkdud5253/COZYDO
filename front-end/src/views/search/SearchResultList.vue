<!-- 검색결과 리스트 -->
<template>
  <v-app>
    <!-- 상단 메뉴  -->
    <div class="search mt-3">
      <v-icon class="goback" color="#fc355d" large @click="goBack">
        mdi-chevron-left
      </v-icon>
      <v-btn color="#fc355d" class="searchButton" dark @click="onChangeMap">
        <v-icon>mdi-map</v-icon>
        <v-spacer></v-spacer>
        지도
      </v-btn>
     
      <v-text-field
        hide-details
        class="searchText"
        v-model="keyword"
        solo
        ma-0
        pa-0
        height="40"
        label="장소검색"
        clearable
        @keypress.enter="onInputKeyword"
      ></v-text-field>
    </div>

    <div class="checkbox">
      <v-radio-group v-model="selected" row>
        <v-radio
          label="내 위치 중심"
          color="#88C8FF"
          value="mylocation"
        ></v-radio>
        <v-radio
          label="지도 중심"
          color="#88C8FF"
          value="mapcenter"
          checked
        ></v-radio>
      </v-radio-group>
    </div>

    <!-- 검색결과(리스트) -->
   <SearchList :value="selected"/>
  
  </v-app>
</template>
<style scoped>
.search {
  margin-left: 3%;
  width: 95%;
  display: flex;
  align-items: center;
  /* margin-top: 5%;  */
}
.searchButton {
  float: left;
  width: 20%;
  height: 40px !important;
  margin-left: 3%;
  margin-right: 7%;
  box-sizing: border-box;
  
}

.searchText {
  align-items: center;
  float: left;
  width: 30%;
  box-sizing: border-box;
  margin-right: 3% !important;  
}


.checkbox {
  width: 90%;
  display: flex;
  margin-left: 5%;
  margin-top: -1%;
} 


.v-divider {
  border-width: thin 0 0 0;
}

.v-rating{
  max-width: 25px;
  padding-right: 30px;
}

.v-list--nav .v-list-item {
    border-radius: 10px;
    margin: 7px;
}

v-list-item-group {
  margin-top: 10px;
}

.v-list-item.v-list-item {
  background: white;
}

</style>

<script>
import SearchList from "@/components/SearchList.vue";
export default {
  name: "SearchResultList",
  components: {SearchList},
  data: function() {
    return {
      items: [],
      keyword: this.$route.query.keyword,
      selected: "mapcenter",
      lat: this.$route.query.lat,
      lon: this.$route.query.lon,
      centerLat: this.$route.query.centerLat,
      centerLon: this.$route.query.centerLon,
      level: this.$route.query.currentLevel,
    };
  },
  methods: {
    goBack() {
      this.$router.push("/");
    },
    onInputKeyword() {
      this.$router.go(
        this.$router.push({
          name: "SearchResultList",
          query: {
            keyword: this.keyword,
            lon: this.lon,
            lat: this.lat,
            currentLevel: this.level,
            centerLat: this.centerLat,
            centerLon: this.centerLon
          },
        })
      );
    },
    onChangeMap() {
      this.$router.push({
        name: "SearchResultMap",
        query: {
          keyword: this.keyword,
          lon: this.lon,
          lat: this.lat,
          currentLevel: this.level,
          centerLat: this.centerLat,
          centerLon: this.centerLon
        },
      });
    },
    
  },
};
</script>
