<template>
  <div>
    <div class="flex space-x-12">
      <div class="overflow-hidden rounded-lg h-36 w-72">
        <img
          :src="giftPack.thumbnailimg"
          :alt="giftPack.thumbnailimg"
          class="object-cover"
        />
      </div>
      <div>
        <div class="flex justify-between">
          <h1 class="text-2xl font-bold">{{ giftPack.gname }}</h1>
        </div>
        <span class="text-lg font-bold">${{ giftPack.price }}</span>
        <p>{{ giftPack.gdescription }}</p>
      </div>
    </div>

    <!-- All items -->

    <div class="mt-5">
      <h2 class="text-xl font-semibold">All items of this pack</h2>
      <div class="grid grid-cols-4 gap-4 mt-4">
        <item-in-pack v-for="item in items" :key="item.cid" :item="item" />
      </div>
    </div>

    <!-- Info -->

    <div class="mt-5">
      <h2 class="text-xl font-semibold">Please give us some information:</h2>
      <div class="grid grid-cols-4 gap-4 mt-4">
        <label class="flex flex-col space-y-2">
          <span>Receiver's Address:</span>
          <input
            class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
            v-model="info.receiveAddress"
            @input="isInfoValid = true"
          />
        </label>
        <label class="flex flex-col space-y-2">
          <span>Receiver's Phone Number:</span>
          <input
            class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
            v-model.number="info.phoneNumber"
            @input="isInfoValid = true"
          />
        </label>
        <label class="flex flex-col space-y-2">
          <span>Date you want to arrive:</span>
          <input
            class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
            v-model="info.dateArrive"
            @input="isInfoValid = true"
          />
        </label>
        <label class="flex flex-col space-y-2">
          <span>Greeting message on the gift box:</span>
          <input
            class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
            v-model="info.greetingCardContent"
            @input="isInfoValid = true"
          />
        </label>
      </div>
    </div>
    <p class="mt-3 text-sm font-medium text-red-500" v-if="!isInfoValid">
      Please fill all required fields
    </p>

    <!-- Separate -->
    <div class="w-full h-px mt-4 bg-gray-400"></div>
    <div class="mt-3">
      <h2 class="text-xl font-semibold">You can choose some addon:</h2>
      <div class="grid grid-cols-4 gap-4 mt-2">
        <addon-card
          v-for="addon in addons"
          :key="addon.cid"
          :addon="addon"
          @selectAddon="selectAddon"
          @removeAddon="removeAddon"
        />
      </div>
    </div>
    <div class="flex justify-center mt-6 mb-5">
      <button
        class="px-4 py-2 text-white rounded-full"
        :class="{
          ' bg-blue-500': !isSent,
          'hover:bg-blue-400': !isSent,
          'bg-green-500': isSent,
        }"
        @click="handleSendGift"
      >
        {{ buttonText }}
      </button>
    </div>
  </div>
</template>

<script>
import AddonCard from '@/components/AddonCard.vue'
import ItemInPack from '@/components/ItemInPack.vue'
export default {
  computed: {
    giftPackId() {
      return this.$route.params.cid
    },
    buttonText() {
      if (this.isSent) return 'Done! Backing to homepage...'
      else if (this.isSending) return 'Sending, please wait...'
      else return 'Send this gift'
    },
  },
  components: {
    AddonCard,
    ItemInPack,
  },
  data() {
    return {
      giftPack: Object,
      addons: [],
      selectedAddons: [],
      addonsNote: [],
      items: [],
      info: {
        receiveAddress: '',
        phoneNumber: null,
        dateArrive: '',
        greetingCardContent: '',
      },
      isInfoValid: true,
      isSent: false,
      isSending: false,
    }
  },
  created() {
    //getGiftPack
    this.$axios
      .get(
        `http://tonydomain.ddns.net:8080/giftapp/api/giftpacks/${this.giftPackId}`
      )
      .then((res) => {
        this.giftPack = res.data
        // getItems
        this.$axios
          .get(
            `http://tonydomain.ddns.net:8080/giftapp/api/giftpackitems/${this.giftPackId}`
          )
          .then((res) => {
            const itemIds = [...res.data.itemid]
            itemIds.map((id) => {
              this.$axios
                .get(`http://tonydomain.ddns.net:8080/giftapp/api/items/${id}`)
                .then((res) => {
                  this.items.push(res.data)
                })
                .catch((err) => console.log(err))
            })
          })
          .catch((err) => console.log(err))
      })
      .catch((err) => console.log(err))

    //getAllAddon
    this.$axios
      .get('http://tonydomain.ddns.net:8080/giftapp/api/addons')
      .then((res) => (this.addons = [...res.data]))
      .catch((err) => console.log(err))
  },
  methods: {
    selectAddon(cid, note) {
      this.selectedAddons.push(cid)
      this.addonsNote.push(note)
    },
    removeAddon(cid, note) {
      const newSelectedAddons = []
      const newAddonsNote = []
      this.selectedAddons.map((addonId) => {
        if (addonId !== cid) newSelectedAddons.push(addonId)
      })
      this.addonsNote.map((addonNote) => {
        if (addonNote !== note) newAddonsNote.push(addonNote)
      })
      this.selectedAddons = [...newSelectedAddons]
      this.addonsNote = [...newAddonsNote]
    },
    handleSendGift() {
      if (
        !this.info.receiveAddress ||
        !this.info.phoneNumber ||
        !this.info.dateArrive ||
        !this.info.greetingCardContent
      ) {
        this.isInfoValid = false
        return
      }
      const data = {
        ...this.info,
        uname: localStorage.getItem('user'),
        giftpackId: this.giftPackId,
        addonId: [...this.selectedAddons],
        content: [...this.addonsNote],
      }
      this.isSending = true
      this.$axios
        .post('http://tonydomain.ddns.net:8080/giftapp/api/user/order', data)
        .then(() => {
          this.isSending = false
          this.isSent = true
          setTimeout(() => {
            this.$router.push('/')
            this.isSent = false
          }, 700)
        })
        .catch((err) => {
          console.log(err)
          this.isSending = false
        })
    },
  },
}
</script>
