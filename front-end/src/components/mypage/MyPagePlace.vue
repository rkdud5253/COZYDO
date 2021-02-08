<template>
  <div class="mx-5 my-3">

    <v-card flat>
      <v-card-title>
        <v-icon color="#fc355d">
          mdi-heart
        </v-icon>
        <h3 class="mx-1">장소 리스트</h3>
      </v-card-title>
      
      <v-progress-linear
        color="#fc355d"
        value="45"
        buffer-value="100"
        rounded
      >
      </v-progress-linear>

      <!-- 장소 리스트 -->
      <v-expansion-panels 
        class="my-1"
        accordion
        multiple
        hover
      >
        <v-expansion-panel
          v-for="(item,i) in 3"
          :key="i"
        >
          <v-expansion-panel-header disable-icon-rotate>
            <h4>스타벅스</h4>
            <template v-slot:actions>
              <v-icon color="white">
                mdi-arrow-right
              </v-icon>
            </template>
          </v-expansion-panel-header>

        </v-expansion-panel>
      </v-expansion-panels>
      
    </v-card>
    
  </div>
</template>

<script>
export default {
  name: "MyPagePlace",
};
</script>

<style scoped>
  .v-expansion-panel--active > .v-expansion-panel-header {
    min-height: 40px;
  }
  .v-item-group {
    position: initial;
  }
  .v-card__subtitle, .v-card__text, .v-card__title {
    padding: 12px;
  }
</style>