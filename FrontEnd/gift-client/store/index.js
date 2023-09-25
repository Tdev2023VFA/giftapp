export const state = () => ({
  cartCounter: 0,
  cartItems: [],
})

export const mutations = {
  ADD_TO_CART(state, item) {
    state.cartCounter++
    state.cartItems.push(item)
  },
  REMOVE_FROM_CART(state, itemId) {
    state.cartCounter--
    const newCartItems = state.cartItems.filter((item) => item.cid !== itemId)
    state.cartItems = [...newCartItems]
  },
}
