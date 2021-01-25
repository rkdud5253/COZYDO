<template>
  <v-app>

    <!-- 지역 및 거리두기 단계 -->
    <div class="region_step">
      <v-container fluid>
        <v-row align="center">
          <v-col
            class="d-flex"
            cols="12"
            sm="6"
          >
            <v-select
              :items="regions"
              label="지역"
              dense
              outlined
              hide-details=""
              color="pink"
            ></v-select>
          </v-col>
          <v-col
            class="d-flex"
            cols="12"
            sm="6"
          >
            <v-select
              :items="items"
              label="거리두기 단계"
              dense
              outlined
              hide-details=""
              color="pink"
            ></v-select>
          </v-col>
        </v-row>
      </v-container>
    </div>


    <!-- 지도 위에 검색창 띄우기 -> style에서 각각 position 맞춰줘야함 -->
    <div class="map_wrap">
      <div class="map_search">
        <!-- 검색창 -->
        <v-toolbar
          class="searchBar"
          dense
          rounded
        >
          <v-text-field
            hide-details
            prepend-icon="mdi-map-marker"
            single-line
            label="장소, 주소 검색"
            color="pink"
          ></v-text-field>

          <v-btn icon>
            <v-icon>mdi-magnify</v-icon>
          </v-btn>

        </v-toolbar>


        <!-- 검색 카테고리 -->
        <div class="category mx-2" style="margin: 20px">
          <v-row
            align="center"
            justify="space-around"
          >
            <v-btn
              depressed
              elevation="2"
              small
              color="white"
            >
              <v-icon small color="red">mdi-plus-box</v-icon>
              선별진료소
            </v-btn>
            <v-btn
              depressed
              elevation="2"
              small
              color="white"
            >
              <v-icon small color="orange">mdi-silverware-variant</v-icon>
              음식점
            </v-btn>
            <v-btn
              depressed
              elevation="2"
              small
              color="white"
            >
              <v-icon small color="brown">mdi-coffee</v-icon>
              카페
            </v-btn>
            <v-btn
              depressed
              elevation="2"
              small
              color="white"
            >
              <v-icon small color="blue">mdi-store-24-hour</v-icon>
              편의점
            </v-btn>
            <v-btn
              depressed
              elevation="2"
              small
              color="white"
            >
              <v-icon small color="black">mdi-dumbbell</v-icon>
              체육시설
            </v-btn>        
          </v-row>
        </div>
      </div>

      <!-- 지도 -->
      <div class="map pa-4" id="map"></div>

    </div>

  </v-app>
</template>

<script>
export default {
  name: 'SearchMap',
  data: function() {
    return {
      map: '',
      initPos: { lat: '37.5665734', lng: '126.978179' },
      houseLat: '',
      houseLng: '', // 좌표값 입력 받기
      dong: '',
      aptName: '',
      ps: '',
      markers: [],
      selected: '',
      // infowindow: '',
      imageSrc:
        'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png',
      imageSize: new kakao.maps.Size(24, 35),

      regions: ['서울', '인천', '대전', '광주', '대구', '부산', '울산', '세종', '제주'],
      items: ['1', '1.5', '2', '2.5', '3'],
    };
  },
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  methods: {
    initMap() {
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(this.initPos.lat, this.initPos.lng),
        level: 7,
      };
      this.map = new kakao.maps.Map(container, options);

      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    },
    addScript() {
      const script = document.createElement('script');
      /* global kakao */

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6489698d36bee11ff3b7992ee0a7981a';
      document.head.appendChild(script);
    },
  },
};
</script>
<style scoped>
#map {
  /* float: right;
  margin-right: 100px;
  margin-top: 15px; */
  width: 480px;
  height: 650px;
  z-index: 1;
}
input {
  margin-right: 10px;
  margin-left: 10px;
}

.map_wrap {
  position: relative;
}
.map_search {
  position: absolute;
  z-index: 2; 
  display: block; 
  margin: 10px;
}
.searchBar {
  width: 430px;
}
</style>
