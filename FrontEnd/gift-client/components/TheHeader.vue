<template>
  <header class="flex items-center justify-between">
    <nuxt-link to="/explore-gifts" class="flex items-center space-x-1">
      <logo />
      <span class="text-2xl font-bold">Gift</span>
    </nuxt-link>
    <div class="flex items-center space-x-3">
      <nuxt-link to="/profile" class="font-semibold hover:underline">{{
        user
      }}</nuxt-link>
      <button title="Logout" class="hover:text-red-200" @click="logoutHandler">
        <power />
      </button>
    </div>
  </header>
</template>

<script>
import Logo from '@/assets/Logo.vue'
import Power from '@/assets/Power.vue'
export default {
  computed: {
    user() {
      return localStorage.getItem('user')
    },
  },
  data() {
    return {
      isUserOptionVisible: false,
    }
  },
  components: {
    Logo,
    Power,
  },
  methods: {
    logoutHandler() {
      localStorage.removeItem('user')
      localStorage.removeItem('role')
      this.$router.push('/sign-up')
    },
  },
}
</script>
