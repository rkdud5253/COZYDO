<template>
    <div>
    </div>
</template>
<script>
import axios from "axios";
export default {
  name: "Authentication",
  components: {
  },
  created() {
    this.param = document.location.href.split("?")[1].split("&")
    //이메일 (@엔코딩된거바꿔야한다.)
    this.front = this.param[0].split("=")[1].split("%40")[0]
    this.end = this.param[0].split("=")[1].split("%40")[1]
    this.email = this.front + "@" + this.end;
    //인증키
    this.authkey = this.param[1].split("=")[1]
    
    axios({
            method: 'get',
            url: 'https://i4a201.p.ssafy.io:8080/user/authentication',
            params:{
              email:this.email,
              authkey:this.authkey
            }
          }).then((res) => {
            alert(res.data.data)
            this.$router.push('/')
          }).catch((err) => {
            console.log(err)
          })
  }
}
</script>