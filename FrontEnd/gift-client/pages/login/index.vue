<template>
  <base-form>
    <h1 class="text-xl font-bold">Log in to send a gift</h1>
    <div class="space-y-3 mt-7">
      <label class="flex flex-col space-y-1 font-semibold">
        <span class="text-sm">Username:</span>
        <input
          class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
          v-model="username"
          @input="clearErrMessage"
        />
      </label>
      <label class="flex flex-col space-y-1 font-semibold">
        <span class="text-sm">Password:</span>
        <input
          class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
          type="password"
          v-model="password"
          @input="clearErrMessage"
          @keydown.enter="loginSubmit"
        />
      </label>
    </div>
    <p class="mt-2 text-sm font-semibold text-red-500" v-if="errMessage">
      {{ errMessage }}
    </p>
    <button
      class="
        mt-5
        py-2.5
        px-6
        font-semibold
        text-white
        transition
        duration-100
        bg-blue-500
        rounded-full
        hover:bg-blue-400
      "
      @click="loginSubmit"
    >
      Get Started
    </button>
    <nuxt-link to="/sign-up" class="mt-4 text-sm hover:text-blue-600"
      >Don't have an account? <span class="font-bold">Sign up</span></nuxt-link
    >
  </base-form>
</template>

<script>
import BaseForm from '@/UI/BaseForm.vue'
import BaseInput from '@/UI/BaseInput.vue'
import BaseButton from '@/UI/BaseButton.vue'
import services from '@/services'
export default {
  components: {
    BaseForm,
    BaseInput,
    BaseButton,
  },
  layout: 'overlay',
  data() {
    return {
      username: '',
      password: '',
      errMessage: '',
    }
  },
  head() {
    return {
      title: 'Log in',
    }
  },
  methods: {
    loginSubmit() {
      if (!this.username || !this.password) {
        this.errMessage = 'Please fill all required fields'
        return
      }
      const info = {
        username: this.username,
        password: this.password,
        accountType: 1,
      }
      services
        .login(info)
        .then((res) => {
          localStorage.setItem('user', JSON.parse(res.config.data).username)
          this.$router.push('/')
          if (info.username === 'admin') {
            localStorage.setItem('role', 'admin')
          }
        })
        .catch(() => {
          this.errMessage =
            'Username or password is incorrect. Please try again'
          this.password = ''
        })
    },
    clearErrMessage() {
      this.errMessage = ''
    },
  },
  created() {
    const user = localStorage.getItem('user')
    if (user) this.$router.push('/')
  },
}
</script>
