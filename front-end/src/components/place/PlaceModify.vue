<template>
   <v-main><!--
    <v-container style="margin-top: 30px">
      <h2 style="font-weight: bold; text-align: center; margin-bottom: 10px">
        수정 제안
      </h2>
      <v-divider></v-divider>

      <div style="max-width: 400px; margin: 15px 25px">
        <p>
          허위 정보는 제안이 거절될 수 있습니다. 서비스의 원할한 운영을 위해
          신중하게 정보를 제안해 주시기 바랍니다.
        </p>

        <div>
          <div style="float: left; margin-top: 20px; margin-right: 13px">
            영업시간
          </div>
          <v-text-field
            style="float: left; width: 70%"
            v-model="message1"
            clearable
          ></v-text-field>
        </div>
        <div style="float: left; margin: 5px 13px 20px 0">폐업여부</div>
        <v-btn-toggle
          v-model="toggle_exclusive"
          class="transparent"
          mandatory
          color="primary"
          style="width: 70%; margin-bottom: 15px"
        >
          <v-btn :value="1" flat style="width: 50%"> 운영중 </v-btn>

          <v-btn :value="2" flat style="width: 50%"> 폐업 </v-btn>
        </v-btn-toggle>
        <v-btn
          style="width: 100%; margin-top: 15px"
          class="ma-2"
          outlined
          color="indigo"
          >작성 완료
        </v-btn>
      </div></v-container
    >--></v-main
  > 
</template>
<script>
// export default {
//   data: () => ({
//     rating: 0,
//     message1: "10:00 - 21:00",
//     rules: [(v) => v.length <= 300 || "Max 300 characters"],
//     toggle_exclusive: 1,
//   }),
// };
</script>
