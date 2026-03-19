import { defineConfig, loadEnv } from 'vite'
import { fileURLToPath, URL } from 'node:url'
import vue from '@vitejs/plugin-vue'

export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '')
  const frontendPort = Number(env.VITE_FRONTEND_PORT || env.FRONTEND_PORT || 5173)
  const backendTarget = env.VITE_BACKEND_TARGET || `http://127.0.0.1:${env.BACKEND_PORT || 8080}`
  const enableProxy = (env.VITE_DEV_PROXY || 'true') === 'true'

  return {
    server: {
      host: '0.0.0.0',
      port: frontendPort,
      open: '/login',
      proxy: enableProxy
        ? {
            '/api': {
              target: backendTarget,
              changeOrigin: true,
              rewrite: (path) => path.replace(/^\/api/, '')
            }
          }
        : undefined
    },
    plugins: [vue()],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      }
    }
  }
})
