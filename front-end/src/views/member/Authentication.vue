<template>
    <div>
    </div>
</template>
<script>
export default {
  name: "Authentication",
  components: {
  },
  created() {
    alert('인증되었습니다');
    this.$router.push('/')
  }
}
</script>