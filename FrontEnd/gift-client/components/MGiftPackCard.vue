<template>
  <section class="pb-3 rounded-lg shadow-lg">
    <div class="h-48 overflow-hidden rounded-md">
      <img :src="giftPack.thumbnailimg" alt="" class="object-cover" />
    </div>
    <div class="px-5 mt-3">
      <div class="flex justify-between">
        <h2 class="text-lg font-bold">{{ giftPack.gname }}</h2>
        <span class="font-semibold">${{ giftPack.price }}</span>
      </div>
      <div class="flex justify-between mt-2">
        <p class="text-sm truncate" style="max-width: 12rem">
          {{ giftPack.gdescription }}
        </p>
        <button
          class="px-2 py-1 text-sm text-white bg-yellow-500 rounded-full  hover:bg-yellow-400"
          @click="handleCloseModal"
        >
          EDIT
        </button>
      </div>
    </div>

    <!-- Edit Modal -->

    <div
      v-if="isEditModalShowed"
      class="fixed top-0 bottom-0 left-0 right-0 flex items-center justify-center bg-gray-500  bg-opacity-70"
    >
      <div class="px-6 py-4 bg-white rounded-md">
        <h3 class="text-lg font-semibold">Item Editors</h3>
        <div class="mt-3 space-y-2">
          <label class="flex flex-col space-y-1">
            <span>Name:</span>
            <input
              type="text"
              class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
              v-model="editingGiftPack.gname"
            /> </label
          ><label class="flex flex-col space-y-1">
            <span>Description:</span>
            <input
              type="text"
              class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
              v-model="editingGiftPack.gdescription"
            /> </label
          ><label class="flex flex-col space-y-1">
            <span>Price:</span>
            <input
              type="text"
              class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
              v-model="editingGiftPack.price"
            /> </label
          ><label class="flex flex-col space-y-1">
            <span>Thumbnail URL:</span>
            <input
              type="text"
              class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
              v-model="editingGiftPack.thumbnailimg"
            />
          </label>
          <label class="flex flex-col space-y-1">
            <span>Pack's items (item's cid, split by ","):</span>
            <input
              type="text"
              class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
              :placeholder="itemsOfPackStringify"
              v-model="editingItem"
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
  data() {
    return {
      itemsOfPack: [],
      editingGiftPack: { ...this.giftPack },
      isEditModalShowed: false,
      isEditDone: false,
      editingItem: '',
    }
  },
  computed: {
    itemsOfPackStringify() {
      return this.itemsOfPack.length === 0
        ? 'No item. Click here to add one!'
        : `Current items: ${this.itemsOfPack.toString()}`
    },
  },
  props: {
    giftPack: {
      type: Object,
    },
  },
  created() {
    this.$axios
      .get(
        `http://tonydomain.ddns.net:8080/giftapp/api/giftpackitems/${this.giftPack.cid}`
      )
      .then((res) => {
        this.itemsOfPack = [...res.data.itemid]
      })
      .catch((err) => console.log(err))
  },
  methods: {
    handleEdit() {
      this.$axios
        .put(
          `http://tonydomain.ddns.net:8080/giftapp/api/giftpacks/${this.editingGiftPack.cid}`,
          this.editingGiftPack
        )
        .then(() => {
          this.$emit('reloadGiftPacks')
          if (!this.editingItem) {
            this.isEditDone = true
            setTimeout(() => {
              this.isEditModalShowed = false
              this.isEditDone = false
            }, 500)
          } else {
            this.$axios
              .delete(
                `http://tonydomain.ddns.net:8080/giftapp/api/giftpackitems/${this.giftPack.cid}`
              )
              .then(() => {
                this.$axios
                  .post(
                    'http://tonydomain.ddns.net:8080/giftapp/api/giftpackitems',
                    {
                      giftpackid: this.giftPack.cid,
                      itemid: this.editingItem.split(','),
                    }
                  )
                  .then((res) => {
                    this.$axios
                      .get(
                        `http://tonydomain.ddns.net:8080/giftapp/api/giftpackitems/${this.giftPack.cid}`
                      )
                      .then((res) => {
                        this.itemsOfPack = [...res.data.itemid]
                        this.isEditDone = true
                        setTimeout(() => {
                          this.isEditModalShowed = false
                          this.isEditDone = false
                        }, 500)
                      })
                      .catch((err) => console.log(err))
                  })
                  .catch((err) => console.log(err))
              })
              .catch((err) => console.log(err))
          }
        })
        .catch((err) => console.log(err))
    },
    handleCloseModal() {
      this.isEditModalShowed = true
      this.editingItem = ''
    },
  },
}
</script>
