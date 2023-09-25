<template>
  <section class="pb-3 rounded-lg shadow-lg">
    <div class="h-48 overflow-hidden rounded-md">
      <img :src="pack.thumbnailimg" alt="" class="object-cover" />
    </div>
    <div class="px-5 mt-3">
      <div class="flex justify-between">
        <h2 class="text-lg font-bold">{{ pack.gname }}</h2>
        <span class="font-semibold">${{ pack.price }}</span>
      </div>
      <div class="flex justify-between mt-2">
        <p class="inline-block text-sm truncate" style="max-width: 12rem">
          {{ pack.gdescription }}
        </p>
        <nuxt-link
          :to="link"
          class="px-2 py-1 text-sm text-white bg-blue-500 rounded-full  hover:bg-blue-400"
        >
          SEND
        </nuxt-link>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      isSelected: false,
    }
  },
  props: {
    pack: {
      type: Object,
    },
  },
  computed: {
    link() {
      return `/explore-gifts/${this.pack.cid}`
    },
  },
}
</script>
