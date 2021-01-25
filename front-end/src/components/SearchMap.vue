<template>
  <v-app>
      <!-- 지도 -->
      <div class="map pa-4" id="map"></div>
      
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
      // markers: [],
      selected: '',
      // infowindow: '',
      imageSrc:
        'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png',
      imageSize: new kakao.maps.Size(24, 35),
      // locPosition: '',
      message: '',
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
            });
          
      }

      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.BOTTOMRIGHT);
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
