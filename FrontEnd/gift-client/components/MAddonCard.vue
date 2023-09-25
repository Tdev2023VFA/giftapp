<template>
  <section class="px-5 py-3 text-gray-200 bg-gray-900 rounded-lg shadow-lg">
    <div class="flex justify-between">
      <h2 class="text-lg font-bold">{{ addon.aname }}</h2>
      <span class="font-semibold">${{ addon.price }}</span>
    </div>
    <div class="flex items-center justify-between smt-2">
      <p class="text-sm truncate" style="max-width: 12rem">
        {{ addon.adescription }}
      </p>
      <button
        class="px-2 py-1 text-sm text-white bg-yellow-500 rounded-full  hover:bg-yellow-400"
        @click="isEditModalShowed = true"
      >
        EDIT
      </button>
    </div>

    <!-- Edit Modal -->

    <div
      v-if="isEditModalShowed"
      class="fixed top-0 bottom-0 left-0 right-0 flex items-center justify-center bg-gray-500  bg-opacity-70"
    >
      <div class="px-6 py-4 text-black bg-white rounded-md">
        <h3 class="text-lg font-semibold">Addon Editors</h3>
        <div class="mt-3 space-y-2">
          <label class="flex flex-col space-y-1">
            <span>Name:</span>
            <input
              type="text"
              class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
              v-model="editingAddon.aname"
            /> </label
          ><label class="flex flex-col space-y-1">
            <span>Description:</span>
            <input
              type="text"
              class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
              v-model="editingAddon.adescription"
            /> </label
          ><label class="flex flex-col space-y-1">
            <span>Price:</span>
            <input
              type="text"
              class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
              v-model="editingAddon.price"
            />
          </label>
        </div>
        <div class="flex justify-end mt-3 space-x-3">
          <button
            class="
              text-sm
              font-semibold
              bg-gray-500
              hover:bg-gray-400
              px-2.5
              py-1
              rounded-full
              text-white
            "
            @click="isEditModalShowed = false"
          >
            CANCEL
          </button>
          <button
            class="
              text-sm
              font-semibold
              hover:bg-yellow-400
              px-2.5
              py-1
              rounded-full
              text-white
            "
            :class="{
              'bg-yellow-500': !isEditDone,
              'bg-green-500': isEditDone,
            }"
            @click="handleEdit"
          >
            {{ isEditDone ? 'DONE!' : 'EDIT' }}
          </button>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  props: {
    addon: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      editingAddon: { ...this.addon },
      isEditModalShowed: false,
      isEditDone: false,
    }
  },
  methods: {
    handleEdit() {
      console.log(this.editingAddon)
      this.$axios
        .put(
          `http://tonydomain.ddns.net:8080/giftapp/api/addons/${this.editingAddon.cid}`,
          this.editingAddon
        )
        .then((res) => {
          if (res.status === 200) {
            this.isEditDone = true
            setTimeout(() => {
              this.isEditModalShowed = false
              this.$emit('reloadAddons')
              this.isEditDone = false
            }, 300)
          }
        })
        .catch((err) => console.log(err))
    },
  },
}
</script>
