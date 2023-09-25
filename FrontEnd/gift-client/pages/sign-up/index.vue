<template>
  <base-form>
    <h1 class="text-xl font-bold">Sign up to send a gift</h1>
    <div class="space-y-3 mt-7">
      <label class="flex flex-col space-y-1 font-semibold">
        <span class="text-sm">Username:</span>
        <input
          v-model="username"
          class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
          @input="clearErrMessage"
        />
      </label>
      <label class="flex flex-col space-y-1 font-semibold">
        <div class="flex justify-between">
          <span class="text-sm">Password:</span>
          <button
            v-if="password"
            class="px-2 text-sm text-gray-600"
            @click="isPasswordVisible = !isPasswordVisible"
          >
            Show
          </button>
        </div>
        <input
          v-model="password"
          :type="isPasswordVisible ? 'text' : 'password'"
          @keydown.enter="signUpSubmit"
          class="px-3 py-2 border border-blue-500 rounded-md focus:ring-1"
          @input="clearErrMessage"
        />
      </label>
    </div>
    <p class="mt-2 text-sm font-semibold text-red-500" v-if="errMessage">
      {{ errMessage }}
    </p>
    <button
      @click="signUpSubmit"
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
    >
      Create my account
    </button>
    <nuxt-link to="/login" class="mt-4 text-sm hover:text-blue-600"
      >Already have an account? <span class="font-bold">Login</span></nuxt-link
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
      isPasswordVisible: false,
    }
  },
  head() {
    return {
      title: 'Sign up',
    }
  },
  methods: {
    signUpSubmit() {
      if (!this.username || !this.password) {
        this.errMessage = 'Please fill all required fields'
        return
      }
      const data = {
        username: this.username,
        password: this.password,
      }
      services
        .signUp(data)
        .then((res) => {
          localStorage.setItem('user', JSON.parse(res.config.data).username)
          this.$router.push('/')
        })
        .catch(() => {
          this.errMessage = 'This username is not available. Try another one'
          this.username = ''
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
