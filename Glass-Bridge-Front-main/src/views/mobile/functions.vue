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

  <div v-if="isMobile" class="portal-container">
    <div class="list-cell">
      <div class="menu-item" v-for="item in store.staticItems" :key="item.text">
        <div class="icon-box" :style="{ backgroundColor: item.color }"></div>
        <span class="app-name">{{ item.text }}</span>
      </div>
    </div>

    <div class="business-list">
      <div v-for="group in store.groupedByCategory" :key="group.name" class="category-section">
        <div class="category-title">{{ group.name }}</div>
        
        <div 
          v-for="app in group.items" 
          :key="app.id" 
          class="list-cell"
          @click="handleEntry(app)"
        >
          <div class="app-icon" v-html="app.svg"></div>
          <div class="app-info">
            <span class="app-name">{{ app.name }}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="empty-space"></div>
  </div>
</template>

<script setup>
import { inject } from 'vue'
import { useRouter } from 'vue-router';
import { useFunctionStore } from '@/stores/functionStore';

const router = useRouter();
const store = useFunctionStore();
const isMobile = inject('isMobile')

const handleEntry = (app) => {
  if (app.routerLink) {
    router.push(app.routerLink);
    return;
  }
  console.warn(`未配置路由: ${app.name}`);
};
</script>

<style scoped>
.portal-container {
  margin: 0;
  padding: 5px;
  overflow: auto;
  padding-bottom: 60px; /* Ensure space for footer */
}

/* 针对 Category 的样式优化 */
.category-title {
  padding: 12px 14px;
  font-size: 13px;
  color: #999;
  background-color: #f7f7f7; /* 浅灰色间隔栏 */
  text-transform: uppercase;
}

.list-cell {
  background: #fff;
  display: flex;
  align-items: center;
  padding: 12px 15px;
  position: relative;
}

/* 微信风格底边线：最后一项不显示线 */
.list-cell:not(:last-child)::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 1px;
  background: #f0f0f0;
}

.app-icon {
  width: 20px;
  height: 20px;
  border-radius: 6px;
  margin-right: 12px;
}

.app-name {
  font-size: 16px;
  color: #333;
}

/* 调整 footer 始终停留在顶部 */
.footer {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 1000;
}

.empty-space {
  height: 40px; /* Footer height to prevent content overlap */
}

/* 非移动端 */
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
</style>