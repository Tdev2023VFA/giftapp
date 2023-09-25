<template>
  <div id="app" class="px-24 pt-24">
    <the-header
      class="fixed top-0 left-0 right-0 px-24 py-5 text-white bg-blue-500"
    />
    <nuxt />
  </div>
</template>

<script>
import TheHeader from '@/components/TheHeader.vue'
export default {
  components: {
    TheHeader,
  },
}
</script>
