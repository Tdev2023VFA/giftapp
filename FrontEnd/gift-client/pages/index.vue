<template>
  <div></div>
</template>

<script>
export default {
  created() {
    const user = localStorage.getItem('user')
    const role = localStorage.getItem('role')
    if (!user) this.$router.push('/sign-up')
    else if (role) this.$router.push('/manage')
    else {
      this.$axios
        .get(`http://tonydomain.ddns.net:8080/giftapp/api/accountinfos/${user}`)
        .then(() => {
          this.$router.push('/explore-gifts')
        })
        .catch(() => {
          this.$router.push('/profile')
        })
    }
  },
}
</script>
