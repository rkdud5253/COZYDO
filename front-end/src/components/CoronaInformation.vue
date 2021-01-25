<template>
  <v-app>
    <div class="information">
      <v-card class="mx-auto" max-width="500" flat>
        <v-container fluid>
          <v-row dense>
            <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
              <v-card>
                <v-img
                  :src="card.src"
                  class="white--text align-end"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  height="200px"
                >
                  <v-card-title v-text="card.title"></v-card-title>
                </v-img>

                <v-card-actions>
                  <div>{{ card.content }}</div>
                  
                  <v-spacer></v-spacer>

                  <v-btn icon>
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>

                  <v-btn icon>
                    <v-icon>mdi-bookmark</v-icon>
                  </v-btn>

                  <v-btn icon>
                    <v-icon>mdi-share-variant</v-icon>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
    </div>
  </v-app>
</template>

<script>
export default {
  name: "CoronaInformation",
  component: {},
  data: function() {
    return {
      cards: [
        {
          title: "title1",
          content: "content1",
          src: "https://www.chla.org/sites/default/files/thumbnails/image/CHLA-What-You-Should-Know-Covid-19-1200x628-02.png",
          flex: 12,
        },
        {
          title: "title2",
          content: "content2",
          src: "http://cdc.go.kr/cdc/img/main/visual_default_main_m02.jpg",
          flex: 12,
        },
        {
          title: "title3",
          content: "content3",
          src: "https://www.hongcheon.go.kr/corona/images/corona_bn_210118_1.jpg",
          flex: 12,
        },
      ],
    };
  },
};
</script>
