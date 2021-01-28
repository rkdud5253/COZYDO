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
      <v-btn color="#FF8199" class="searchButton" dark @click="onChangeMap">
        <v-icon>mdi-map</v-icon>
        <v-spacer></v-spacer>
        지도
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
                @click="onMyLocation"
              ></v-radio>
              <v-radio
                label="지도 중심"
                color="#88C8FF"
                value="mapcenter"
                @click="onMapCenter"
                checked
              ></v-radio>
              
            </v-radio-group>
      <!-- <v-checkbox class="pr-6" label="내위치중심" value="mylocation" color="#88C8FF"></v-checkbox>
      <v-checkbox class="pr-6" label="지도중심" value="map" color="#88C8FF"></v-checkbox> -->
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
            <v-list-item v-if="index<=30" :key="item.title">
              <template >
                <v-list-item-content @click="onChangeDetail(item.placeIdx)">
                  <v-list-item-title v-text="item.placeName"></v-list-item-title>

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
                  <v-list-item-subtitle v-text="item.roadAddressName"></v-list-item-subtitle>
                  <v-list-item-action-text v-text="item.action"></v-list-item-action-text>
                  
                </v-list-item-content>

                <v-list-item-avatar>
                <v-list-item-subtitle v-text="item.distance.substring(2,5)+'m'"></v-list-item-subtitle>
                </v-list-item-avatar>
                
              </template>
            </v-list-item>

            <v-divider
              v-if="index <= 30-1"
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
.theme--light.v-card {
   border: 0.5px solid;
}

.v-divider {
  border: 0.5px solid;
  border-width: thin 0 0 0;
}

</style>

<script>
import axios from "axios";
export default {
  name: 'SearchResultList',
  component: {

  },
  data: function () {
  return {
    items: [
    ],
    keyword: this.$route.query.keyword,
    selected: 'mapcenter',
    lat: 37.5031784,
    lon: 126.8798427,
    centerLat: 37.4968436,
    centerLng: 127.0328341
    }
  },
  methods: {
    goBack () {
        this.$router.push('/')
      },
    onInputKeyword() {
      this.$router.go(this.$router.push({name: 'SearchResultList', query: {keyword: this.keyword}}));
    },
    onChangeMap() {
      this.$router.push({name: 'SearchResultMap', query: {keyword: this.keyword}})
    },
    onChangeDetail(placeIdx){
      this.$router.push({name: 'PlaceDetail', params: {placeIdx: placeIdx}});
    },
    onMapCenter() {
      console.log(this.centerLat + " " + this.centerLng)
        axios.get('http://i4a201.p.ssafy.io:8080/map/list', {
        params: {
          keyword: this.keyword,
            lat: this.centerLat,
            lon: this.centerLng
        }
      })
      .then((res) => {
        console.log(res);
        this.items = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    },
    onMyLocation() {
      console.log(this.lat + " " + this.lon)
        axios.get('http://i4a201.p.ssafy.io:8080/map/list', {
        params: {
          keyword: this.keyword,
            lat: this.lat,
            lon: this.lon
        }
      })
      .then((res) => {
        console.log(res);
        this.items = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    },
    
  },
  created() {
    this.onMapCenter()
  }
}
</script>