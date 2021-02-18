<!-- 검색결과 페이지 리스트 -->
<template>
  <v-app>
     {{ onList(value) }}
     <!-- 장소 리스트화 -->
    <v-card class="overflow-y-auto" max-height="570" outlined>
      <v-list nav
        dense
        >
        <v-list-item-group class="pt-1 pb-1">
          <template v-for="(item, index) in items">
            <v-list-item v-if="index <= 30" :key="item.title">
              <template>
                <v-list-item-content style="color:black;" @click="onChangeDetail(item.placeIdx)">
                  {{ item.placeName }}
                  <v-rating
                    background-color="red lighten-3"
                    length="1"
                    :value=1
                    color="red"
                    size="20"
                    readonly 
                  ></v-rating>
                  <p v-if="Math.round(item.rating * 10) / 10 !== 0" style="max-width:20px; font-size:12px;">{{ Math.round(item.rating * 10) / 10 }}</p>
                  <p v-if="Math.round(item.rating * 10) / 10 === 0" style="max-width:50px; font-size:8px;">(리뷰없음)</p>
                  <v-list-item-title>
                  </v-list-item-title>

                  <v-list-item-subtitle
                    class="text--primary"
                    v-text="item.headline"
                  ></v-list-item-subtitle>
                  
                  <v-list-item-subtitle style="color:black"
                    v-text="item.roadAddressName"
                  ></v-list-item-subtitle>
                  <v-list-item-action-text
                    v-text="item.action"
                  ></v-list-item-action-text>

                  <v-list-item-subtitle style="color:black"
                    class="text--primary"
                    v-text="item.tel"
                  ></v-list-item-subtitle>
                </v-list-item-content>


                <v-list-item-avatar>
                  <v-list-item-subtitle style="color:black">
                    {{Math.round(parseFloat(item.distance)*1000)+'m'
                  }}</v-list-item-subtitle>
                </v-list-item-avatar>
              </template>
            </v-list-item>

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

.v-list-item.v-list-item {
  border: 1px rgb(226, 220, 221) solid;
}


.v-list-item__avatar {
  width: 60px !important;
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
        if(value !== this.oldValue){
            this.onChangeList(value)
            this.oldValue = value;
        }
    },
    onChangeList(value){
      if(value==='mapcenter'){
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
          this.items = res.data;
        })
        .catch((err) => {
          console.log(err);
        });


      }
      else if(value==='mylocation'){
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
          this.items = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
      }
    },
  },
  
};
</script>
