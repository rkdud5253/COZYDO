<template>
  <v-app>
    <CoronaInformation />
  </v-app>
</template>

<script>
import CoronaInformation from "@/components/CoronaInformation.vue";
export default {
  name: "Information",
  components: {
      CoronaInformation,
  },
};
</script>
