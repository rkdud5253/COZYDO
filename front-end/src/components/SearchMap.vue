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
      initPos: { lat: '37.5013068', lng: '127.0385654' },
      items: [
      ],
      keyword: this.$route.query.keyword,
      lat: this.$route.query.lat,
      lon: this.$route.query.lon,
      centerLat: this.$route.query.centerLat,
      centerLon: this.$route.query.centerLon,
      level: this.$route.query.currentLevel,
      markers: [],
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
        level: 4,
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
        
          var moveLatLon = new kakao.maps.LatLng(lat, lon);
          // 지도 중심을 이동 시킵니다
          this.map.setCenter(moveLatLon);


          // 마커 커스텀 이미지
          // var imageSrc = 'https://ifh.cc/g/kvQ1zL.png';
          var imageSrc2 = 'https://ifh.cc/g/MrAjpC.png';
          var imageSize = new kakao.maps.Size(42, 43);
          var imageOption = {offset: new kakao.maps.Point(27, 69)};

          // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc2, imageSize, imageOption);

          // 마커 20개 생성
          for(var i=0; i< this.items.length; i++){
              var tmpLat = this.items[i].placeLat;
              var tmpLng = this.items[i].placeLon;
              tmpLat = Number(tmpLat);
              tmpLng = Number(tmpLng);
             
              var markerPosition  = new kakao.maps.LatLng(tmpLat, tmpLng); 
             
              var marker = new kakao.maps.Marker({
                  map: this.map,
                  position: markerPosition,
                  title: this.items[i].placeName,
                  image: markerImage
              });

              // var content = '<div style="padding:5px;">'+this.items[i].placeName+'</div>';
              // var content = '<div v-on:click="onChangeDetail('+this.items[i].placeIdx+')">'+this.items[i].placeName+'</div>';
              var content = '<div class="overlay_info" style="background-color:#fff; border-radius: 6px; margin-bottom: 12px; float:left;position: relative; border: 1px solid #ccc; border-bottom: 2px solid #ddd;">'+
                '<a onclick="javascript:onChangeDetail({1})" style="display: block; background: #d95050; background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center; text-decoration: none; color: #fff; padding:5px 30px 5px 10px; font-size: 14px; border-radius: 6px">'+
                '<strong>'+'<router-link to="PlaceDetail">'+this.items[i].placeName +'</router-link>'+'</strong></a></div>';

              // var content = '<div>'+this.items[i].placeName+'</div>';

              var overlay = new kakao.maps.CustomOverlay({
                content: content,
                map: this.map,
                position: markerPosition   
              });

              overlay.setMap(null);

              this.markers.push(marker)
              // // 마커가 지도 위에 표시되도록 설정합니다
              // this.markers[i].setMap(this.map);
              marker.setMap(this.map);
              
              kakao.maps.event.addListener(marker, 'click', this.makeOverListener(overlay, content, this.map, markerPosition));
              
          }


    },
   makeOverListener : (overlay, content, map, markerPosition) => {
     return () => {
       overlay.setMap(null);
       overlay = new kakao.maps.CustomOverlay({
        content: content,
        map: map,
        position: markerPosition   
      });
      overlay.setMap(map);
    };
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
          axios.get('https://i4a201.p.ssafy.io:8080/map/list', {
          params: {
            keyword: this.keyword,
            lat: this.centerLat,
            lon: this.centerLon,
            level : this.level
          }
        })
        .then((res) => {
          // console.log(res);
          this.items = res.data;
          var size = this.items.length
          // this.addMarker();
          this.addMarker(this.centerLat, this.centerLon);
          // 최근 받아온 마커들을 제외한 모든 마커 삭제
           for(var j=0; j<this.markers.length-size; j++){
             this.markers[j].setMap(null)
           }
        })
        .catch((err) => {
          console.log(err);
        });
      }
      else if(value=='mylocation'){
         axios.get('https://i4a201.p.ssafy.io:8080/map/list', {
          params: {
            keyword: this.keyword,
            lat: this.lat,
            lon: this.lon,
            level : this.level
          }
        })
        .then((res) => {
          // console.log(res);
          this.items = res.data;
          var size = this.items.length
          // this.addMarker();
          this.addMarker(this.lat, this.lon);
          // 최근 받아온 마커들을 제외한 모든 마커 삭제
           for(var j=0; j<this.markers.length-size; j++){
             this.markers[j].setMap(null)
           }
        })
        .catch((err) => {
          console.log(err);
        });
      }
    },
    // onChangeDetail: function(placeIdx) {
    //   // return () => {
    //     console.log(placeIdx)
    //   this.$router.push({
    //     name: "PlaceDetail",
    //     params: { placeIdx: placeIdx, level: this.level },
    //   });
    //   // }
      
    // }
    
    onChangeDetail : (placeIdx) => {
      return () => {
      console.log(placeIdx)
      };
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


.overlay_info {border-radius: 6px; margin-bottom: 12px; float:left;position: relative; border: 1px solid #ccc; border-bottom: 2px solid #ddd;background-color:#fff;}
.overlay_info:nth-of-type(n) {border:0; box-shadow: 0px 1px 2px #888;}
.overlay_info a {display: block; background: #d95050; background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center; text-decoration: none; color: #fff; padding:12px 36px 12px 14px; font-size: 14px; border-radius: 6px 6px 0 0}
.overlay_info a strong {background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/place_icon.png) no-repeat; padding-left: 27px;}
.overlay_info .desc {padding:14px;position: relative; min-width: 190px; height: 56px}
.overlay_info:after {content:'';position: absolute; margin-left: -11px; left: 50%; bottom: -12px; width: 22px; height: 12px; background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png) no-repeat 0 bottom;}
</style>
