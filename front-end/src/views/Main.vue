<template>
  <div class="Main">
    <h1>Main</h1>
    <MainMap />
  </div>
</template>

<script>
// @ is an alias to /src
import MainMap from "@/components/MainMap.vue";

export default {
  name: "Main",
  components: {
    MainMap
  }
};
</script>
