<template>
  <div>
    <h2 class="text-xl font-semibold">
      {{
        hasInfo
          ? 'Here is your account info. Click on each one to update'
          : 'Before we go, let give us some information about you'
      }}
    </h2>
    <div class="grid justify-between grid-cols-4 gap-5 mt-3">
      <label class="flex flex-col space-y-2">
        <span>Display name:</span>
        <input
          placeholder="Click to add your display name"
          class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
          v-model="displayName"
          @input="isValid = true"
        />
      </label>
      <label class="flex flex-col space-y-2">
        <span>Address:</span>
        <input
          placeholder="Click to add your address"
          v-model="address"
          class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
          @input="isValid = true"
        />
      </label>
      <label class="flex flex-col space-y-2">
        <span>Phone number:</span>
        <input
          placeholder="Click to add your phone number"
          v-model.number="phoneNumber"
          class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
          @input="isValid = true"
        />
      </label>
      <label class="flex flex-col space-y-2">
        <span>Email:</span>
        <input
          placeholder="Click to add your phone email"
          v-model="email"
          @keydown.enter="handleUpdateInfo"
          @input="isValid = true"
          class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
        />
      </label>
    </div>
    <p class="text-sm text-red-700" v-if="!isValid">Please fill all fields</p>
    <div class="flex justify-end mt-4">
      <button
        @click="handleUpdateInfo"
        class="
          bg-blue-500
          px-2.5
          py-1.5
          rounded-full
          text-white
          hover:bg-blue-400
          uppercase
          text-sm
        "
      >
        {{ !hasInfo ? `Let's go` : 'Update your info' }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      displayName: '',
      address: '',
      phoneNumber: null,
      email: '',
      hasInfo: false,
      isValid: true,
    }
  },
  methods: {
    handleUpdateInfo() {
      const data = {
        uname: localStorage.getItem('user'),
        disname: this.displayName,
        uaddress: this.address,
        phonenum: this.phoneNumber,
        email: this.email,
      }
      if (!this.hasInfo) {
        if (
          !this.displayName ||
          !this.address ||
          !this.phoneNumber ||
          !this.email
        ) {
          this.isValid = false
        } else {
          this.$axios
            .post(
              'http://tonydomain.ddns.net:8080/giftapp/api/accountinfos',
              data
            )
            .then(() => this.$router.push('/'))
            .catch((err) => console.log(err))
        }
      } else {
        this.$axios
          .post(
            'http://tonydomain.ddns.net:8080/giftapp/api/accountinfos',
            data
          )
          .then(() => {
            this.$router.push('/')
          })
          .catch((err) => console.log(err))
      }
    },
  },
  created() {
    const user = localStorage.getItem('user')
    this.$axios
      .get(`http://tonydomain.ddns.net:8080/giftapp/api/accountinfos/${user}`)
      .then((res) => {
        this.hasInfo = true
        this.displayName = res.data.disname
        this.address = res.data.uaddress
        this.phoneNumber = res.data.phonenum
        this.email = res.data.email
      })
      .catch((err) => {
        this.hasInfo = false
      })
  },
}
</script>
