<template>
  <div v-if="!isMobile" class="mobile-only-page">
    <!-- 半透明遮罩，保证文字清晰 -->
    <div class="mask">
      <!-- 主体内容 -->
      <div class="content">
        <h1 class="title">当前页面仅支持移动端访问</h1>
        <p class="main-text">请使用 <span>手机或平板设备</span> 打开此页面</p>
        <p class="main-text1">如果你是错误地进入该页，请点击左上角返回首页</p>
        <p class="sub-text">This page is only available on mobile devices.</p>

      </div>
    </div>
  </div>
  <div v-if="isMobile" class="page-bg">
    <div class="checkin-card">
      <!-- 地图区域 -->
      <div id="map" class="map"></div>

      <div id="reposition" @click="getLocation" style="margin-top: 10px; color: blue; cursor: pointer;">重新定位</div>
      
      <!-- 操作区 -->
      <div class="action-area">
        <button class="loc-btn" @click="LocateCheckin">
          <div class="checkin-text">上班打卡</div>
          <div class="time-text">{{ currentTime }}</div>
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, inject } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()
const route = useRoute()

const currentTime = ref('')
const LocatedStatus = ref(false)
let timeInterval = null

// 更新当前时间
const updateTime = () => {
  const now = new Date()
  const hours = String(now.getHours()).padStart(2, '0')
  const minutes = String(now.getMinutes()).padStart(2, '0')
  const seconds = String(now.getSeconds()).padStart(2, '0')
  currentTime.value = `${hours}:${minutes}:${seconds}`
}

// 从 App.vue 注入移动端状态
const isMobile = inject('isMobile')

const map = ref(null)
const position = ref(null)
const marker = ref(null) // 存储当前标记
const offsetLat = ref(-0.00279)
const offsetLng = ref(0.00495)

const LocateCheckin = () => {
  if (LocatedStatus.value == true) {
    ElMessage.success('打卡成功！')
  } else {
    ElMessage.error('请先定位成功后再打卡！')
  }
}

const getLocation = () => {
  if (!map.value) return
  map.value.plugin('AMap.Geolocation', () => {
    const geolocation = new AMap.Geolocation({
      enableHighAccuracy: true, // 高精度
      timeout: 10000,           // 10秒超时
      buttonPosition: 'RB',     // 定位按钮位置
      GeoLocationFirst: true,
      zoomToAccuracy: true      // 定位成功后自动放大到定位点
    })

    geolocation.getCurrentPosition((status, result) => {
      if (status === 'complete') {
        position.value = {
          lat: result.position.lat+offsetLat.value,
          lng: result.position.lng+offsetLng.value
        }
        map.value.setCenter([position.value.lng, position.value.lat])
        
        // 清除旧标记
        if (marker.value) {
          marker.value.setMap(null)
        }
        
        // 在地图上加一个新标记
        marker.value = new AMap.Marker({
          position: [position.value.lng, position.value.lat],
          map: map.value
        })

        LocatedStatus.value = true
      } else {
        alert('定位失败：' + result.message)
      }
    })
  })
}

onMounted(() => {
  // 初始化时间
  updateTime()
  // 每秒更新一次时间
  timeInterval = setInterval(updateTime, 1000)

  map.value = new AMap.Map('map', {
    center: [110.697564, 29.395224], // 默认中心
    zoom: 17,                       // 固定缩放等级
    zooms: [17, 17],                // 限制最小最大等级一致
    resizeEnable: true
  })

  // 禁止用户拖动和缩放
  map.value.setStatus({
    dragEnable: false,
    zoomEnable: false,
    doubleClickZoom: false,
    touchZoom: false,
    scrollWheel: false
  })

  // 页面加载时自动获取位置
  getLocation()
})

onUnmounted(() => {
  // 清除定时器
  if (timeInterval) {
    clearInterval(timeInterval)
  }
})
</script>

<style scoped>
.mobile-only-page {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  overflow: hidden;

  /* 🔹 在这里替换成你找到的插画背景图片 */
  background-image: url('../../assets/mobile_error.png');
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center top;

  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f8fafc; /* 图片加载失败时的兜底背景 */
}

/* 遮罩层，防止图片太亮影响文字阅读 */
.mask {
  width: 100%;
  height: 100%;
  background: linear-gradient(
  to bottom,
  rgba(255, 255, 255, 0.75),
  rgba(255, 255, 255, 0.95)
  );

  display: flex;
  align-items: flex-end;
  justify-content: center;
  padding-bottom: 13%; /* 控制文字整体下移 */
}

.content {
  text-align: center;
  max-width: 480px;
  padding: 0 24px;
}

.title {
  font-size: 22px;
  font-weight: 600;
  color: #1f2937;
  margin-bottom: 20px;
}

.main-text {
  font-size: 16px;
  color: #374151;
  margin-bottom: 8px;
}

.main-text1 {
  font-size: 16px;
  color: #374151;
  margin-bottom: 25px;
  margin-top: 8px;
}

.main-text span {
  color: #2563eb;
  font-weight: 600;
}

.sub-text {
  font-size: 13px;
  color: #9ca3af;
  margin-bottom: 28px;
}

/* 整个页面背景 */
.page-bg {
  min-height: 80vh;
  background: white;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding-top: 0px;
  overflow: hidden;
}

/* 中心卡片 */
.checkin-card {
  width: 100%;
  max-width: 720px;
  border-radius: 10px;
  padding: 24px 24px 30px;
  background-color: white;
}

.reposition {
  width: 100%;
  align-items: center;
  justify-self: center;
  margin-top: 8px;
  font-size: 14px;
}

/* 地图 */
.map {
  width: 105%;
  align-items: center;
  justify-self: center;
  border-radius: 4px;
  overflow: hidden;
  border: 1px solid #e5e7eb;
}

/* 操作区 */
.action-area {
  cursor: pointer;
  transition: all 0.2s;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 8px;
  margin-top: 15%;
}

/* 按钮 */
.loc-btn {
  width: 250px;
  height: 250px;
  border-radius: 50%;
  border: none;
  background: linear-gradient(135deg, #409eff, #66b1ff);
  color: #fff;
}

.checkin-text {
  font-size: 30px;
  margin-bottom: 8px;
  font-weight: bold;
}

.time-text {
  font-size: 22px;
  cursor: pointer;
  transition: all 0.2s;
}

.loc-btn:hover {
  transform: translateY(-1px);
  box-shadow: 0 6px 16px rgba(64, 158, 255, 0.3);
}

/* 点击时的阴影效果 */
.loc-btn:active {
  box-shadow: 4px 2px 5px rgba(0, 0, 0, 0.3);
}

/* 手机适配 */
@media (max-width: 768px) {
  .checkin-card {
    margin: 0 14px;
    padding: 0px 5px 24px;
  }

  .map {
    height: 170px;
    margin-top: 10px;
  }

  .loc-btn {
    width: 170px;
    height: 170px;
  }
}
</style>