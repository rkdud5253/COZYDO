<template>
  <v-app>
     {{ onList(value) }}
     <!-- 장소 리스트화 -->
    <v-card class="overflow-y-auto" max-height="570" outlined>
      <v-list nav
        dense
        >
        <v-subheader>장소</v-subheader>
        <v-divider></v-divider>
        <v-list-item-group class="pt-1 pb-1">
          <template v-for="(item, index) in items">
            <v-list-item v-if="index <= 30" :key="item.title">
              <template>
                <v-list-item-content @click="onChangeDetail(item.placeIdx)">
                  {{ item.placeName }} 
                  <v-rating
                    background-color="red lighten-3"
                    length="1"
                    :value=1
                    color="red"
                    size="20"
                    readonly
                  ></v-rating>
                  <p style="max-width:20px; font-size:12px; ">3.5</p>
                  
                  <v-list-item-title >
                  </v-list-item-title>

                  <v-list-item-subtitle
                    class="text--primary"
                    v-text="item.headline"
                  ></v-list-item-subtitle>
                  
                  <v-list-item-subtitle
                    v-text="item.roadAddressName"
                  ></v-list-item-subtitle>
                  <v-list-item-action-text
                    v-text="item.action"
                  ></v-list-item-action-text>

                  <v-list-item-subtitle
                    class="text--primary"
                    v-text="item.tel"
                  ></v-list-item-subtitle>
                </v-list-item-content>


                <v-list-item-avatar>
                  <v-list-item-subtitle v-if="item.distance.charAt(2) == '0'">{{
                    item.distance.substring(3, 5) + "m"
                  }}</v-list-item-subtitle>
                  <v-list-item-subtitle v-if="item.distance.charAt(2) != '0'">{{
                    item.distance.substring(2, 5) + "m"
                  }}</v-list-item-subtitle>
                </v-list-item-avatar>
              </template>
            </v-list-item>

            <!-- <v-divider v-if="index <= 30 - 1" :key="index"></v-divider> -->
          </template>
        </v-list-item-group>
      </v-list>
    </v-card>
  </v-app>
</template>
<style scoped>

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
/* .v-list--nav.v-list--dense {
  margin-bottom: 10px !important;
  margin-top: 10px !important;
} */

.v-list-item.v-list-item {
  background: white;
}

.v-item-group {
  background: lightgray;
}
</style>

<script>
import axios from "axios";
export default {
  name: "SearchList",
  components: {},
  props: ['value'],
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
      oldValue: "",
    };
  },
  methods: {
    onChangeDetail(placeIdx) {
      this.$router.push({
        name: "PlaceDetail",
        params: { placeIdx: placeIdx, level: this.level },
      });
    },
    onList(value){
        if(value != this.oldValue){
            console.log(value + " " + this.oldValue)
            this.onChangeList(value)
            this.oldValue = value;
        }
    },
    onChangeList(value){
      if(value=='mapcenter'){
        this.items = [],
        axios
        .get("https://i4a201.p.ssafy.io:8080/map/list", {
          params: {
            keyword: this.keyword,
            lat: this.centerLat,
            lon: this.centerLon,
            level: this.level,
          },
        })
        .then((res) => {
          console.log(res);
          this.items = res.data;
        })
        .catch((err) => {
          console.log(err);
        });


      }
      else if(value=='mylocation'){
        this.items = [],
        axios
        .get("https://i4a201.p.ssafy.io:8080/map/list", {
          params: {
            keyword: this.keyword,
            lat: this.lat,
            lon: this.lon,
            level: this.level,
          },
        })
        .then((res) => {
          console.log(res);
          this.items = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
      }
    },
  },
  created() {
    // this.onMapCenter();
  },
};
</script>
