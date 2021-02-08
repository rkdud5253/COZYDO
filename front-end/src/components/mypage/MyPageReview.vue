<template>
  <div class="mx-5 my-3">

    <v-card flat>
      <v-card-title>
        <v-icon color="#fc355d">
          mdi-file-document
        </v-icon>
        <h3 class="mx-1">내가 쓴 리뷰</h3>
      </v-card-title>
      
      <v-progress-linear
        color="#fc355d"
        value="45"
        buffer-value="100"
        rounded
      >
      </v-progress-linear>

      <!-- 리뷰 리스트 -->
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
          <v-expansion-panel-header>
            <h4>스타벅스</h4>
          </v-expansion-panel-header>
          <v-expansion-panel-content class="mt-1">
            9시까지 하는 줄 알았는데 저희 동네는 8시까지 하더라구요. 참고하세요!
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
      
    </v-card>

  </div>
</template>

<script>
export default {
  name: "MyPageReview",
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