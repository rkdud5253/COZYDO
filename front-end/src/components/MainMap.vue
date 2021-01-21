<template>
  <div>
    <div id="select">
      <input type="radio" value="CS2" v-model="selected" />
      <label for="convi">편의점</label>
      <input type="radio" value="MT1" v-model="selected" />
      <label for="convi">대형마트</label>
      <input type="radio" value="SC4" v-model="selected" />
      <label for="convi">학교</label>
      <input type="radio" value="BK9" v-model="selected" />
      <label for="convi">은행</label>
      <input type="radio" value="CE7" v-model="selected" />
      <label for="convi">카페</label>
      <input type="radio" value="HP8" v-model="selected" />
      <label for="convi">병원</label>
    </div>
    <div class="map" id="map"></div>
  </div>
</template>
<script>
export default {
  name: 'SearchMap',
  data() {
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
}
input {
  margin-right: 10px;
  margin-left: 10px;
}
</style>
