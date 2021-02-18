<!-- 메인 맵 -->
<template>
  <v-app>
    <!-- 지역 및 거리두기 단계 -->
    <div class="region_step">
      <v-container fluid>
        <v-row align="center">
          <v-col class="d-flex" cols="6" sm="6">
            <v-select
              :items="regions"
              label="지역"
              dense
              outlined
              hide-details=""
              color="#fc355d"
              v-model="currentRegion"
              item-color="pink"
            ></v-select>
          </v-col>
          <v-col class="d-flex" cols="6" sm="6">
            <v-select
              :items="items"
              label="거리두기 단계"
              dense
              outlined
              hide-details=""
              color="#fc355d"
              v-model="currentLevel"
              item-color="pink"
            ></v-select>
          </v-col>
        </v-row>
      </v-container>
    </div>

    <!-- 지도 위에 검색창 띄우기 -> style에서 각각 position 맞춰줘야함 -->
    <div class="map_wrap">
      <div class="map_search">
        <!-- 검색창 -->
        <v-toolbar class="searchBar" dense rounded>
          <v-text-field
            hide-details
            prepend-inner-icon="mdi-map-marker"
            single-line
            label="장소, 주소 검색"
            color="#fc355d"
            @keypress.enter="onInputKeyword"
            v-model="keyword"
          ></v-text-field>

          <v-btn icon @click="onInputKeyword">
            <v-icon color="blue">mdi-magnify</v-icon>
          </v-btn>
        </v-toolbar>

        <!-- 검색 카테고리 -->
        <div class="category mx-2" style="margin: 20px">
          <v-row align="center" justify="space-around">
            <v-btn
              class="btn"
              depressed
              elevation="2"
              color="white"
              @click="onClinkCatecory"
            >
              <v-icon small color="red">mdi-plus-box</v-icon>
              <h4>선별진료소</h4>
            </v-btn>
            <v-btn
              class="btn"
              depressed
              elevation="2"
              color="white"
              @click="onClinkCatecory"
            >
              <v-icon small color="orange">mdi-silverware-variant</v-icon>
              <h4>음식점</h4>
            </v-btn>
            <v-btn
              class="btn"
              depressed
              elevation="2"
              color="white"
              @click="onClinkCatecory"
            >
              <v-icon small color="brown">mdi-coffee</v-icon>
              <h4>카페</h4>
            </v-btn>
            <v-btn
              class="btn"
              depressed
              elevation="2"
              color="white"
              @click="onClinkCatecory"
            >
              <v-icon small color="blue">mdi-store-24-hour</v-icon>
              <h4>편의점</h4>
            </v-btn>
            <v-btn
              class="btn"
              depressed
              elevation="2"
              color="white"
              @click="onClinkCatecory"
            >
              <v-icon small color="black">mdi-dumbbell</v-icon>
              <h4>체육시설</h4>
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
import axios from "axios";
export default {
  name: "SearchMap",
  data: function() {
    return {
      map: "",
      initPos: { lat: "37.5013068", lng: "127.0385654" },

      regions: [
        "강원",
        "경기",
        "경남",
        "경북",
        "광주",
        "대구",
        "대전",
        "부산",
        "서울",
        "세종",
        "울산",
        "인천",
        "전남",
        "전북",
        "제주",
        "충남",
        "충북",
      ],
      items: ["1", "1.5", "2", "2.5", "3"],
      keyword: "",
      currentRegion: "",
      currentLevel: "",
      crawlingLocations: [],

      currentLat: "",
      currentLon: "",
      location: [],
      centerLat: "",
      centerLon: "",
    };
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },

  methods: {
    // 내 현재 위치 백이랑 통신
    myLocation() {
      axios
        .get("https://i4a201.p.ssafy.io:8080/crawling/location", {
          params: {
            lat: this.currentLat,
            lon: this.currentLon,
          },
        })
        .then((res) => {
          this.crawlingLocations = res.data;
          if (this.crawlingLocations) {
            // 내 위도, 경도 보내면 지금 현재 지역과 레벨 받아오기
            this.currentRegion = this.crawlingLocations.object.city;
            this.currentLevel = this.crawlingLocations.object.level;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    initMap() {
      let container = document.getElementById('map');
      let options = {
        center: new kakao.maps.LatLng(this.initPos.lat, this.initPos.lng),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      
      // 현재 위치 마커 추가
      if (navigator.geolocation) {
          this.nowlocation(this.map);
      }
          
     
      let zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.BOTTOMRIGHT);
    
    },
    nowlocation(map) {
      // GeoLocation을 이용해서 접속 위치 얻어오기

      // arrow function을 써야 함수 밖에서도 this값에 손댈 수 있음
      navigator.geolocation.getCurrentPosition((position) =>{

        let lat = position.coords.latitude, // 위도
          lon = position.coords.longitude; // 경도

        let moveLatLon = new kakao.maps.LatLng(lat, lon);
        map.setCenter(moveLatLon);

        this.centerLat = lat
        this.centerLon = lon

        kakao.maps.event.addListener(map, "dragend", () => {
          // 지도 중심좌표를 얻어옴
          let latlng = map.getCenter();

          this.centerLat = latlng.getLat();
          this.centerLon = latlng.getLng();
        });
        let markerPosition = new kakao.maps.LatLng(lat, lon);
      
        let marker = new kakao.maps.Marker({
          map: map,
          position: markerPosition,
        });
        // // 마커가 지도 위에 표시되도록 설정
        marker.setMap(map);
      });

      // 검색 페이지로 현재 경도 위도 넘기기
      navigator.geolocation.getCurrentPosition(
        (pos) => {
          this.gettingLocation = false;
          this.location = pos;
        
          this.currentLat = this.location.coords.latitude;
          this.currentLon = this.location.coords.longitude;

          this.myLocation();
        },
        (err) => {
          this.gettingLocation = false;
          this.errorStr = err.message;
        }
      );
    },
    addScript() {
      const script = document.createElement("script");
      /* global kakao */

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6489698d36bee11ff3b7992ee0a7981a";
      document.head.appendChild(script);
    },

    // 카테고리 버튼 클릭했을 때
    onClinkCatecory: function(event) {

      this.keyword = event.target.innerText;

      // 1, 1.5, 2, 2.5, 3 -> 1, 2, 3, 4, 5
      // console.log(parseFloat(this.currentLevel)*2-1)

      if (this.keyword !== "") {
        this.$router.push({
          name: "SearchResultList",
          query: {
            keyword: this.keyword,
            lon: this.currentLon,
            lat: this.currentLat,
            centerLon: this.centerLon,
            centerLat: this.centerLat,
            currentLevel: parseFloat(this.currentLevel)*2-1,
          },
        });
      }
    },

    // 검색어 입력했을 때
    onInputKeyword() {

      this.$router.push({
        name: "SearchResultList",
        query: {
          keyword: this.keyword,
          lon: this.currentLon,
          lat: this.currentLat,
          centerLon: this.centerLon,
          centerLat: this.centerLat,
          currentLevel: parseFloat(this.currentLevel)*2-1,
        },
      });
    },
  },
};
</script>
<style scoped>
#map {
  width: 0 auto;
  height: 640px;
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
  width: 95%;
}
.searchBar {
  width: 460px;
}
.btn {
  height: 26px !important;
  min-width: 50px !important;
  padding: 10px !important; 
  font-size: 12px !important;
}
@media (min-width: 320px) and (max-width: 419px) {
    .btn {
      height: 20px;
      min-width: 36px;
      padding: 5px !important;
      font-size: 5px;
    }
}
</style>
