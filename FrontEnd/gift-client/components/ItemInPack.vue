<template>
  <section class="pb-3 rounded-lg shadow-lg">
    <div class="h-48 overflow-hidden rounded-md">
      <img :src="item.thumbnailimg" alt="" class="object-cover" />
    </div>
    <div class="px-5 mt-3">
      <div class="flex justify-between">
        <h2 class="text-lg font-bold">{{ item.itemName }}</h2>
        <span class="font-semibold">${{ item.price }}</span>
      </div>
      <div class="flex justify-between mt-2">
        <p class="text-sm truncate" style="max-width: 12rem">
          {{ item.itemDescription }}
        </p>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  props: {
    item: {
      type: Object,
      required: true,
    },
  },
}
</script>
