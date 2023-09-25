import axios from 'axios'

const apiClient = axios.create({
  baseURL: 'http://tonydomain.ddns.net:8080/giftapp/api',
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
  },
})

export default {
  signUp(account) {
    return apiClient.post('/accounts/signup', account)
  },
  login(account) {
    return apiClient.post('/accounts/signin', account)
  },
  setAccountInfo(data) {
    return apiClient.post('/accountinfos', data)
  },
  getAccountInfo(username) {
    return apiClient.get(`/accountinfos/${username}`)
  },
  getItems() {
    return apiClient.get('/items')
  },
  postItem(data) {
    return apiClient.post('/items', data)
  },
  putItem(itemId, data) {
    return apiClient.put(`/items/${itemId}`, data)
  },
}
