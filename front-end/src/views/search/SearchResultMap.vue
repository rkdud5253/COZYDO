<template>
  <v-app>
    <!-- 상단 메뉴 -->
    <div class="search">
      <v-icon class="goback"
        color="pink"
        large
        @click="goBack"
        >
        mdi-chevron-left
      </v-icon>
      <v-btn color="#FF8199" class="searchButton" dark @click="onChangeList">
        <v-icon>mdi-format-list-bulleted-square</v-icon>
        <v-spacer></v-spacer>
        리스트
      </v-btn>
      <v-text-field class="searchText"
            v-model="keyword"
            solo
            label="장소검색"
            clearable
            @keypress.enter="onInputKeyword"
      ></v-text-field>    
    </div>
    
    <div class="checkbox">
            <v-radio-group
              v-model="selected"
              row
            >
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
    <!-- 검색결과(지도) -->
   <SearchMap :value="selected"/>
    
  </v-app>

  
</template>
<style scoped>
.search {
  width: 95%;
  margin: 10% auto;
  margin-bottom: -2%;
  display: flex;
  margin-top: 5%;
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
  margin-top: -3%;
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
    keyword : this.$route.query.keyword,
    selected: 'mapcenter',
    }
  },
  methods: {
    goBack () {
        this.$router.push('/')
      },
    onInputKeyword() {
      this.$router.push({name: 'SearchResultMap', query: {keyword: this.keyword}})
    },
    onChangeList() {
      this.$router.push({name: 'SearchResultList', query: {keyword: this.keyword}})
    },
  },
}
</script>