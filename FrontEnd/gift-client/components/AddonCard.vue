<template>
  <section class="px-5 py-3 text-gray-200 bg-gray-900 rounded-lg shadow-lg">
    <div class="flex justify-between">
      <h2 class="text-lg font-bold">{{ addon.aname }}</h2>
      <span class="font-semibold">${{ addon.price }}</span>
    </div>
    <div class="flex items-center justify-between mt-2">
      <input
        type="text"
        class="
          w-3/5
          px-1.5
          py-1
          text-white
          bg-transparent
          border border-blue-500
          rounded-md
          focus:ring-1
          placeholder-gray-700
        "
        :placeholder="addon.adescription"
        v-model="note"
      />
      <button
        class="px-2 py-1 text-sm text-white rounded-full"
        :class="{
          'bg-green-500': !isSelected,
          'hover:bg-green-400': !isSelected,
          'bg-red-500': isSelected,
          'hover:bg-red-400': isSelected,
        }"
        @click="handleToggleAddon"
        :disabled="!note"
      >
        {{ isSelected ? 'REMOVE -' : 'ADD +' }}
      </button>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      isSelected: false,
      note: '',
    }
  },
  props: {
    addon: {
      type: Object,
      required: true,
    },
  },
  methods: {
    handleToggleAddon() {
      this.isSelected = !this.isSelected
      if (this.isSelected) {
        this.$emit('selectAddon', this.addon.cid, this.note)
      } else {
        this.note = ''
        this.$emit('removeAddon', this.addon.cid, this.note)
      }
    },
  },
}
</script>
