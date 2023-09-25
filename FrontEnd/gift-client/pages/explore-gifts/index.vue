<template>
  <div class="mb-8">
    <div class="grid grid-cols-4 gap-4">
      <gift-card v-for="pack in giftPacks" :key="pack.cid" :pack="pack" />
    </div>
  </div>
</template>

<script>
import GiftCard from '@/components/GiftCard.vue'

export default {
  data() {
    return {
      giftPacks: [],
    }
  },
  components: {
    GiftCard,
  },
  created() {
    this.$axios
      .get('http://tonydomain.ddns.net:8080/giftapp/api/giftpacks')
      .then((res) => (this.giftPacks = [...res.data]))
      .catch((err) => console.log(err))
  },
}
</script>
