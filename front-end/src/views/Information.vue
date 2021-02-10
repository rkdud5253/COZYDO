<template>
  <v-app>
    <CoronaInformation />
    <CoronaChart /> 
    <CoronaSidoList />
  </v-app>
</template>

<script>
import CoronaInformation from "@/components/corona/CoronaInformation.vue";
import CoronaChart from "@/components/corona/CoronaChart.vue";
import CoronaSidoList from "@/components/corona/CoronaSidoList.vue";
export default {
  name: "Information",
  components: {
      CoronaInformation,
      CoronaChart,
      CoronaSidoList
  },
};
</script>
