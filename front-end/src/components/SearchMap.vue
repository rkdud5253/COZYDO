<template>
  <v-app>
      <!-- 지도 -->
      <div class="map pa-4" id="map"></div>
      {{ onChangeMap(value) }}
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: 'SearchMap',
  data: function() {
    return {
      map: '',
      initPos: { lat: '37.4942585', lng: '126.884217' },
      items: [
      ],
      keyword: this.$route.query.keyword,
      lat: 37.5031784,
      lon: 126.8798427,
      centerLat: 37.4968436,
      centerLng: 127.0328341,
    };
  },
  props: ['value'],
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addScript();
  },
  methods: {
    initMap() {
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(this.initPos.lat, this.initPos.lng),
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);
      
      // 현재 위치 마커 추가
      if (navigator.geolocation) {
          this.nowlocation(this.map);
      }
          
     
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.BOTTOMRIGHT);
    
    },
    nowlocation(map) {
      // GeoLocation을 이용해서 접속 위치를 얻어옵니다
          navigator.geolocation.getCurrentPosition(function(position) {
              var lat = position.coords.latitude, // 위도
                  lon = position.coords.longitude; // 경도
              
              console.log(typeof(lat));
              var markerPosition  = new kakao.maps.LatLng(lat, lon); 
             
            
               var marker = new kakao.maps.Marker({
                  map: map,
                  position: markerPosition
              });
              // // 마커가 지도 위에 표시되도록 설정합니다
              marker.setMap(map);
            });
    },
    addMarker(lat, lon) {
      // 검색결과 마커 추가
          var markers = [];
          
          var moveLatLon = new kakao.maps.LatLng(lat, lon);
    
          // 지도 중심을 이동 시킵니다
          this.map.setCenter(moveLatLon);
          for(var i=0; i< 20; i++){
              var tmpLat = this.items[i].placeLat;
              var tmpLng = this.items[i].placeLon;
              tmpLat = Number(tmpLat);
              tmpLng = Number(tmpLng);

              var markerPosition  = new kakao.maps.LatLng(tmpLat, tmpLng); 
             
               var marker = new kakao.maps.Marker({
                  map: this.map,
                  position: markerPosition,
                  title: this.items[i].placeName
              });
              markers.push(marker)
              // // 마커가 지도 위에 표시되도록 설정합니다
              markers[i].setMap(this.map);
          }
          
    },
    addScript() {
      const script = document.createElement('script');
      /* global kakao */

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6489698d36bee11ff3b7992ee0a7981a';
      document.head.appendChild(script);
    },
    onChangeMap(value){
      if(value=='mapcenter'){
          axios.get('http://i4a201.p.ssafy.io:8080/map/list', {
          params: {
            keyword: this.keyword,
            lat: this.centerLat,
            lon: this.centerLng
          }
        })
        .then((res) => {
          // console.log(res);
          this.items = res.data;
          console.log(this.items)
          // this.addMarker();
          this.addMarker(this.centerLat, this.centerLng);
        })
        .catch((err) => {
          console.log(err);
        });
      }
      else if(value=='mylocation'){
         axios.get('http://i4a201.p.ssafy.io:8080/map/list', {
          params: {
            keyword: this.keyword,
            lat: this.lat,
            lon: this.lon
          }
        })
        .then((res) => {
          // console.log(res);
          this.items = res.data;
          console.log(this.items)
          // this.addMarker();
          this.addMarker(this.lat, this.lon);
        })
        .catch((err) => {
          console.log(err);
        });
      }
    }
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
