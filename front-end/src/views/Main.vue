<!-- 메인 화면 -->
<template>
  <v-app>
    <div class="mainmap">
      <MainMap />
    </div>
  </v-app>
</template>

<script>
// @ is an alias to /src
import MainMap from "@/components/MainMap.vue";

export default {
  name: "Main",
  components: {
    MainMap,
  }
};
</script>
