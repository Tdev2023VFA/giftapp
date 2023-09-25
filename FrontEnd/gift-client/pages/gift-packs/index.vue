<template>
  <div>
    <h2 class="text-lg font-bold">Added items</h2>
    <div class="grid grid-cols-4 gap-4">
      <added-item v-for="item in cartItems" :key="item.cid" :item="item" />
    </div>
  </div>
</template>

<script>
import AddedItem from '@/components/AddedItem.vue'
export default {
  components: { AddedItem },
  data() {
    return {}
  },
  computed: {
    cartItems() {
      return this.$store.state.cartItems
    },
  },
}
</script>
