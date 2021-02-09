<template>
  <v-app>
    <CoronaInformation />
    <CoronaChart/>
  </v-app>
</template>

<script>
import CoronaInformation from "@/components/corona/CoronaInformation.vue";
import CoronaChart from "@/components/corona/CoronaChart.vue";
export default {
  name: "Information",
  components: {
      CoronaInformation,
      CoronaChart
  },
};
</script>
