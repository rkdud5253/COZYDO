<template>
  <v-app>

    <div class="search">
      <v-btn color="primary" class="searchButton">
        <v-icon>mdi-format-list-bulleted-square</v-icon>
        지도
      </v-btn>
     
       <v-text-field class="searchText"
            v-model="message"
            label="장소검색"
            outlined
            clearable
          ></v-text-field>
    </div>
    
    <div class="checkbox">
      <v-checkbox class="pr-6" v-model="checkbox" label="내위치중심" value="mylocation"></v-checkbox>
      <v-checkbox class="pr-6" v-model="checkbox" label="지도중심" value="map"></v-checkbox>
    </div>

   
 
   <v-list two-line>
      <v-subheader>장소</v-subheader>
      <v-list-item-group
        v-model="selected"
        active-class="pink--text"
        multiple
      >
        <template v-for="(item, index) in items">
          <v-list-item :key="item.title">
            <template v-slot:default="{ active }">
              <v-list-item-content>
                <v-list-item-title v-text="item.title"></v-list-item-title>

                <v-list-item-subtitle
                  class="text--primary"
                  v-text="item.headline"
                ></v-list-item-subtitle>

                <v-list-item-subtitle v-text="item.subtitle"></v-list-item-subtitle>
              </v-list-item-content>

              <v-list-item-action>
                <v-list-item-action-text v-text="item.action"></v-list-item-action-text>

                <v-icon
                  v-if="!active"
                  color="grey lighten-1"
                >
                  mdi-star-outline
                </v-icon>

                <v-icon
                  v-else
                  color="yellow darken-3"
                >
                  mdi-star
                </v-icon>
              </v-list-item-action>
            </template>
          </v-list-item>

          <v-divider
            v-if="index < items.length - 1"
            :key="index"
          ></v-divider>
        </template>
      </v-list-item-group>
    </v-list>
  </v-app>

  
</template>
<style scoped>
.search {
  width: 90%;
  margin: 10% auto;
  margin-bottom: 0%;
  display: flex;
}

.searchButton {
  float : left;
  width: 20%;
  box-sizing: border-box;
  border: 0 auto; 
  margin-right : 5%;
  margin-top : 2%;
}

.searchText {
  float : left;
  width: 30%;
  box-sizing: border-box;
}

.checkbox {
  width: 90%;
  display: flex;
  margin-left:5%;
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
      {title: '스타벅스', subtitle:'서울시 강남구 역삼동', action: '1588-1588'},
      {title: '스타벅스', subtitle:'서울시 강남구 역삼동', action: '1588-1588'},
      {title: '스타벅스', subtitle:'서울시 강남구 역삼동', action: '1588-1588'},
    ]
    }
  },
}
</script>