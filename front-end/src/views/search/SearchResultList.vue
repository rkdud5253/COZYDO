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
      <v-btn color="#FF8199" class="searchButton" dark href="/searchResultMap">
        <v-icon>mdi-format-list-bulleted-square</v-icon>
        지도
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

   
    <v-card
      class="overflow-y-auto"
      max-height="570"
      outlined
    >
      <v-list>
        <v-subheader>장소</v-subheader>
        <v-divider></v-divider>
        <v-list-item-group>

          <template v-for="(item, index) in items">
            <v-list-item :key="item.title">
              <template >
                <v-list-item-content>
                  <v-list-item-title v-text="item.title"></v-list-item-title>

                  <v-list-item-subtitle
                    class="text--primary"
                    v-text="item.headline"
                  ></v-list-item-subtitle>
                  <v-rating
                    :value="item.star"
                    background-color="red lighten-3"
                    length="5"
                    dense
                    color="red"
                    size="20"
                    readonly
                  ></v-rating>
                  <v-list-item-subtitle v-text="item.subtitle"></v-list-item-subtitle>
                  <v-list-item-action-text v-text="item.action"></v-list-item-action-text>
                  
                </v-list-item-content>

                <v-list-item-avatar>
                <v-list-item-subtitle v-text="item.distance"></v-list-item-subtitle>
                </v-list-item-avatar>
                
              </template>
            </v-list-item>

            <v-divider
              v-if="index < items.length - 1"
              :key="index"
            ></v-divider>
          </template>
        </v-list-item-group>
      </v-list>
    </v-card>
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
.theme--light.v-card {
   border: 0.5px solid;
}

.v-divider {
  border: 0.5px solid;
  border-width: thin 0 0 0;
}

</style>

<script>

export default {
  name: 'SearchResultList',
  component: {

  },
  data: function () {
  return {
    items: [
      {title: '스타벅스1', subtitle:'서울시 강남구 역삼동', action: '1588-1588', distance:'100m', star:3 },
      {title: '스타벅스2', subtitle:'서울시 강남구 역삼동', action: '1588-1588', distance:'200m', star:2},
      {title: '스타벅스3', subtitle:'서울시 강남구 역삼동', action: '1588-1588', distance:'300m', star:1},
      {title: '스타벅스4', subtitle:'서울시 강남구 역삼동', action: '1588-1588', distance:'400m', star:2},
      {title: '스타벅스5', subtitle:'서울시 강남구 역삼동', action: '1588-1588', distance:'500m', star:3},
      {title: '스타벅스6', subtitle:'서울시 강남구 역삼동', action: '1588-1588', distance:'600m', star:4},
      {title: '스타벅스7', subtitle:'서울시 강남구 역삼동', action: '1588-1588', distance:'700m', star:5},
      {title: '스타벅스8', subtitle:'서울시 강남구 역삼동', action: '1588-1588', distance:'800m', star:4},
      {title: '스타벅스9', subtitle:'서울시 강남구 역삼동', action: '1588-1588', distance:'900m', star:3},
      {title: '스타벅스10', subtitle:'서울시 강남구 역삼동', action: '1588-1588', distance:'1000', star:2},
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