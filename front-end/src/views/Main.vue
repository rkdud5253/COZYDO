<template>
  <v-app>
    
    <div class="table">
      <v-card flat>
        <v-tabs
          color="pink"
          fixed-tabs
        >
          <v-tab>Map</v-tab>
          <v-tab>Information</v-tab>

          <v-tab-item>
            <MainMap />
          </v-tab-item>

          <v-tab-item>
            <CoronaInformation />
          </v-tab-item>
        </v-tabs>
      </v-card>
    </div>

  </v-app>
</template>

<script>
// @ is an alias to /src
import MainMap from "@/components/MainMap.vue";
import CoronaInformation from "@/components/CoronaInformation.vue";

export default {
  name: "Main",
  components: {
    MainMap,
    CoronaInformation
  }
};
</script>
