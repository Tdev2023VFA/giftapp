<template>
  <input
    :type="type"
    class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
  />
</template>

<script>
export default {
  props: {
    type: {
      type: String,
      default: 'text',
    },
  },
}
</script>
