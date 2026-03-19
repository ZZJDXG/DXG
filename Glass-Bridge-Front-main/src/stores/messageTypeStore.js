import { defineStore } from 'pinia'

export const useMessageTypeStore = defineStore('messageType', {
  state: () => ({
    messageTypes: []
  }),
  actions: {
    loadMessageTypes() {
      // Frontend mock data; replace with backend API when available.
      this.messageTypes = [
        { id: 'notification', name: '通知' },
        { id: 'alert', name: '警告' },
        { id: 'reminder', name: '提醒' },
        { id: 'marketing', name: '营销' },
        { id: 'system', name: '系统' }
      ]
    }
  }
})
