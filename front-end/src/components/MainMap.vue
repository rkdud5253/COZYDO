<template>
  <v-app>

    <!-- 지역 및 거리두기 단계 -->
    <div class="region_step">
      <v-container fluid>
        <v-row align="center">
          <v-col
            class="d-flex"
            cols="6"
            sm="6"
          >
            <v-select
              :items="regions"
              label="지역"
              dense
              outlined
              hide-details=""
              color="pink"
              v-model="currentRegion"
              item-color="pink"
            ></v-select>
          </v-col>
          <v-col
            class="d-flex"
            cols="6"
            sm="6"
          >
            <v-select
              :items="items"
              label="거리두기 단계"
              dense
              outlined
              hide-details=""
              color="pink"
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
            @keypress.enter="onInputKeyword"
            v-model="keyword"
          ></v-text-field>

          <v-btn
            icon
            @click="onInputKeyword"
          >
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
              @click="onClinkCatecory"
            >
              <v-icon small color="red">mdi-plus-box</v-icon>
              선별진료소
            </v-btn>
            <v-btn
              depressed
              elevation="2"
              small
              color="white"
              @click="onClinkCatecory"
            >
              <v-icon small color="orange">mdi-silverware-variant</v-icon>
              음식점
            </v-btn>
            <v-btn
              depressed
              elevation="2"
              small
              color="white"
              @click="onClinkCatecory"
            >
              <v-icon small color="brown">mdi-coffee</v-icon>
              카페
            </v-btn>
            <v-btn
              depressed
              elevation="2"
              small
              color="white"
              @click="onClinkCatecory"
            >
              <v-icon small color="blue">mdi-store-24-hour</v-icon>
              편의점
            </v-btn>
            <v-btn
              depressed
              elevation="2"
              small
              color="white"
              @click="onClinkCatecory"
            >
              <v-icon small color="black">mdi-dumbbell</v-icon>
              체육시설
            </v-btn>        
          </v-row>
        </div>
      </div>

      <!-- 지도 -->
      <div class="map pa-4" id="map"></div>
      <!-- <div id="result" style="color: white">여기</div> -->
      <!-- {{ currentLat }} -->
    </div>

  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: 'SearchMap',
  data: function() {
    return {
      map: '',
      initPos: { lat: '37.5665734', lng: '126.978179' },

      regions: ['강원', '경기', '경남', '경북', '광주', '대구', '대전', '부산', '서울', '세종', '울산', '인천', '전남', '전북', '제주', '충남', '충북'],
      items: ['1', '1.5', '2', '2.5', '3'],
      keyword: '',
      currentRegion: '',
      currentLevel: '',
      crawlingLocations: [

      ],

      currentLat: '',
      currentLon: '',
      location: [],
      centerLat: '',
      centerLon: '',
    };
  },
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addScript();
  },

  methods: {
    myLocation() {
      axios.get('http://i4a201.p.ssafy.io:8080/crawling/location', {
        params: {
          lat: this.currentLat,
          lon: this.currentLon
        }
      })
      .then((res) => {
        console.log(res);
        this.crawlingLocations = res.data;
        if (this.crawlingLocations) {
          // console.log(this.crawlingLocations.object)
          this.currentRegion = this.crawlingLocations.object.city
          this.currentLevel = this.crawlingLocations.object.level
        }
      })
      .catch((err) => {
        console.log(err);
      });
    },
    initMap() {
      
      
      // 현재 위치 마커 추가
      if (navigator.geolocation) {
          this.map = this.nowlocation(this.map);
      }
      else {
        var container = document.getElementById('map');
        var options = {
        center: new kakao.maps.LatLng(this.initPos.lat, this.initPos.lng),
        level: 7,
      };
      var map = new kakao.maps.Map(container, options);
      var zoomControl = new kakao.maps.ZoomControl();
      map.addControl(zoomControl, kakao.maps.ControlPosition.BOTTOMRIGHT);
      this.map = map
      }

    },
    nowlocation(map) {
      // GeoLocation을 이용해서 접속 위치를 얻어옵니다
          navigator.geolocation.getCurrentPosition(function(position) {
              var lat = position.coords.latitude, // 위도
                  lon = position.coords.longitude; // 경도
              
              
              var container = document.getElementById('map');
              var options = {
                center: new kakao.maps.LatLng(lat, lon),
                level: 7,
              };
              map = new kakao.maps.Map(container, options);
                    kakao.maps.event.addListener(map, 'dragend', function() {        
        
                  // 지도 중심좌표를 얻어옵니다 
                  var latlng = map.getCenter(); 
                  
                  // var message = '변경된 지도 중심좌표는 ' + latlng.getLat() + ' 이고, ';
                  // message += '경도는 ' + latlng.getLng() + ' 입니다';
                  
                  // var resultDiv = document.getElementById('result');  
                  // resultDiv.innerHTML = message;
                  
                  this.centerLat = latlng.getLat()
                  this.centerLon = latlng.getLng()
                  console.log(this.centerLat, this.centerLon)
                  
              });
              var markerPosition  = new kakao.maps.LatLng(lat, lon); 
              // console.log(markerPosition);
            
               var marker = new kakao.maps.Marker({
                  map: map,
                  position: markerPosition
              });
              // // 마커가 지도 위에 표시되도록 설정합니다
              marker.setMap(map);

              // this.addMarker();
              var zoomControl = new kakao.maps.ZoomControl();
              map.addControl(zoomControl, kakao.maps.ControlPosition.BOTTOMRIGHT);
            });

      // 현재 경도 위도 넘기기
      navigator.geolocation.getCurrentPosition(pos => {
        this.gettingLocation = false;
        this.location = pos;
        // console.log(this.location.coords.latitude)
        this.currentLat = this.location.coords.latitude
        this.currentLon = this.location.coords.longitude

        this.myLocation()
        
      }, err => {
        this.gettingLocation = false;
        this.errorStr = err.message;
      })
    },
    addScript() {
      const script = document.createElement('script');
      /* global kakao */

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6489698d36bee11ff3b7992ee0a7981a';
      document.head.appendChild(script);
    },

    // 카테고리 버튼 클릭했을 때
    onClinkCatecory: function (event) {
      // console.log('클릭')
      // console.log(event.target.innerText)
      this.keyword = event.target.innerText
      // console.log(this.keyword)
      if (this.keyword != '') {
        this.$router.push({name: 'SearchResultList', query: {keyword: this.keyword, lon: this.currentLon, lat: this.currentLat, currentLevel: this.currentLevel}})
      }
    },

    // 검색어 입력했을 때
    onInputKeyword() {
      // var center = this.map.getCenter();
      // console.log(this.map.getBounds().toString());
      // console.log(center.getLat())
      // console.log(center.getLng())

      // console.log(this.map.getCenter().getLat())
      // console.log(this.map.getCenter().getLng())

      this.$router.push({name: 'SearchResultList', query: {keyword: this.keyword, lon: this.currentLon, lat: this.currentLat,  centerLon: this.centerLon, centerLat: this.centerLat, currentLevel: this.currentLevel}})
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
  width: 460px;
}
</style>