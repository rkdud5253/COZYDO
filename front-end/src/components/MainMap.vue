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
      <!-- <div id="result">여기</div> -->
      <!-- {{ currentLat }} -->
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

      regions: ['강원', '경기', '경남', '경북', '광주', '대구', '대전', '부산', '서울', '세종', '울산', '인천', '전남', '전북', '제주', '충남', '충북'],
      items: ['1', '1.5', '2', '2.5', '3'],
      keyword: '',
      currentRegion: '서울',
      currentLevel: '2.5',

      currentLat: '',
      currentLon: '',
      location: [],
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


      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
          navigator.geolocation.getCurrentPosition(function(position) {
              
            var lat = position.coords.latitude, // 위도
                lon = position.coords.longitude; // 경도

              // document.getElementById("mapLocation").innerHTML = lat;

              var markerPosition  = new kakao.maps.LatLng(lat, lon); 
              console.log(markerPosition);
              // 마커를 생성합니다
              var marker = new kakao.maps.Marker({
                  position: markerPosition
              });
              var container = document.getElementById('map');
              var options = {
                center: new kakao.maps.LatLng(lat, lon),
                level: 7,
              };
              var map = new kakao.maps.Map(container, options);
              // // 마커가 지도 위에 표시되도록 설정합니다
              marker.setMap(map);
              
              var zoomControl = new kakao.maps.ZoomControl();
              map.addControl(zoomControl, kakao.maps.ControlPosition.BOTTOMRIGHT);
            });        
      }
      // 현재 경도 위도 넘기기
      navigator.geolocation.getCurrentPosition(pos => {
        this.gettingLocation = false;
        this.location = pos;
        // console.log(this.location.coords.latitude)
        this.currentLat = this.location.coords.latitude
        this.currentLon = this.location.coords.longitude
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
      this.$router.push({name: 'SearchResultList', query: {keyword: this.keyword, lon: this.currentLon, lat: this.currentLat, currentLevel: this.currentLevel}})
    },

    // 검색어 입력했을 때
    onInputKeyword() {
      let center = this.map.getCenter();
      // console.log(center.getLat())
      // console.log(center.getLng())

      this.$router.push({name: 'SearchResultList', query: {keyword: this.keyword, lon: this.currentLon, lat: this.currentLat,  centerLon: center.getLng(), centerLat: center.getLat(), currentLevel: this.currentLevel}})
    },

  },

  // watch: {
  //       kakao.maps.event.addListener(map, 'center_changed', function() {
        
  //       // 지도의  레벨을 얻어옵니다
  //       var level = map.getLevel();

  //       // 지도의 중심좌표를 얻어옵니다 
  //       var latlng = map.getCenter(); 

  //       var message = '<p>지도 레벨은 ' + level + ' 이고</p>';
  //       message += '<p>중심 좌표는 위도 ' + latlng.getLat() + ', 경도 ' + latlng.getLng() + '입니다</p>';

  //       var resultDiv = document.getElementById('result');
  //       resultDiv.innerHTML = message;

  //     });
  // }

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
