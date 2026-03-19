<template>
  <div class="sidebar">
    <div class="logo-section">
      <div class="logo">
        <img src="../../assets/favicon.ico" alt="/1" class="logo-img" />
      </div>
      
      <div class="title">
        <p class="dxgyzt">大峡谷一站通</p>
        <p class="dxgyzt-en">Grand Canyon One-Stop</p>
      </div>
    </div>

    <nav class="menu">
      <div class="menu-item" :class="{ active: isActive('1') }" @click="handleMenuClick('1')">
        <svg class="icon" viewBox="0 -1 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
          <polyline points="9 22 9 12 15 12 15 22"></polyline>
        </svg>
        <span>首页</span>
      </div>

      <div v-if="isMenuVisible('2-1') || isMenuVisible('2-2') || isMenuVisible('2-3') || isMenuVisible('2-4') || isMenuVisible('2-5') || isMenuVisible('2-6')" class="menu-group">
        <div class="menu-item" @click="isPersonalExpanded = !isPersonalExpanded">
          <div class="flex-row">
            <svg class="icon" viewBox="0 -1 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"/>
              <circle cx="12" cy="7" r="4"/>
            </svg>
            <span>个人中心</span>
          </div>
          <div class="close-btn" :class="{ 'is-collapsed': !isPersonalExpanded }"></div>
        </div>

        <div class="submenu-wrapper" :class="{ 'is-open': isPersonalExpanded }">
          <div class="submenu-content">
            <div class="submenu">
              <div v-if="isMenuVisible('2-1')" class="submenu-item" :class="{ active: isActive('2-1') }" @click.stop="handleMenuClick('2-1')">审批中心</div>
              <div v-if="isMenuVisible('2-2')" class="submenu-item" :class="{ active: isActive('2-2') }" @click.stop="handleMenuClick('2-2')">报销申请</div>
              <div v-if="isMenuVisible('2-1')" class="submenu-item" :class="{ active: isActive('2-1') }" @click.stop="handleMenuClick('2-3')">工资条</div>
              <div v-if="isMenuVisible('2-2')" class="submenu-item" :class="{ active: isActive('2-2') }" @click.stop="handleMenuClick('2-2')">我的排班</div>
              <div v-if="isMenuVisible('2-1')" class="submenu-item" :class="{ active: isActive('2-1') }" @click.stop="handleMenuClick('2-1')">我的积分</div>
              <div v-if="isMenuVisible('2-2')" class="submenu-item" :class="{ active: isActive('2-2') }" @click.stop="handleMenuClick('2-2')">积分抽奖</div>
            </div>
          </div>
        </div>
      </div>

      <!-- 组织管理 -->
      <div v-if="isMenuVisible('4-1') || isMenuVisible('4-2')" class="menu-group">
        <div class="menu-item" @click="isOrganizationExpanded = !isOrganizationExpanded">
          <div class="flex-row">
            <svg class="icon" viewBox="0 -1 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <rect x="16" y="16" width="6" height="6" rx="1"/>
              <rect x="2" y="16" width="6" height="6" rx="1"/>
              <rect x="9" y="2" width="6" height="6" rx="1"/>
              <path d="M5 16v-3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v3"/>
              <path d="M12 12V8"/>
            </svg>
            <span>组织管理</span>
          </div>
          <div class="close-btn" :class="{ 'is-collapsed': !isOrganizationExpanded }"></div>
        </div>

        <div class="submenu-wrapper" :class="{ 'is-open': isOrganizationExpanded }">
          <div class="submenu-content">
            <div class="submenu">
              <div v-if="isMenuVisible('4-1')" class="submenu-item" :class="{ active: isActive('4-1') }" @click.stop="handleMenuClick('4-1')">部门信息维护</div>
              <div v-if="isMenuVisible('4-2')" class="submenu-item" :class="{ active: isActive('4-2') }" @click.stop="handleMenuClick('4-2')">职级信息维护</div>
            </div>
          </div>
        </div>
      </div>

      <!-- 组织管理 -->
      <div v-if="isMenuVisible('4-1') || isMenuVisible('4-2')" class="menu-group">
        <div class="menu-item" @click="isAttendanceExpanded = !isAttendanceExpanded">
          <div class="flex-row">
            <svg class="icon" viewBox="0 -1 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M11 14h1v4"/>
              <path d="M16 2v4"/>
              <path d="M3 10h18"/>
              <path d="M8 2v4"/>
              <rect x="3" y="4" width="18" height="18" rx="2"/>
            </svg>
            <span>考勤管理</span>
          </div>
          <div class="close-btn" :class="{ 'is-collapsed': !isAttendanceExpanded }"></div>
        </div>

        <div class="submenu-wrapper" :class="{ 'is-open': isAttendanceExpanded }">
          <div class="submenu-content">
            <div class="submenu">
              <div v-if="isMenuVisible('4-1')" class="submenu-item" :class="{ active: isActive('4-1') }" @click.stop="handleMenuClick('4-1')">部门信息维护</div>
              <div v-if="isMenuVisible('4-2')" class="submenu-item" :class="{ active: isActive('4-2') }" @click.stop="handleMenuClick('4-2')">职级信息维护</div>
            </div>
          </div>
        </div>
      </div>

      <div v-if="isMenuVisible('5-1') || isMenuVisible('5-2')" class="menu-group">
        <div class="menu-item" @click="isHRExpanded = !isHRExpanded">
          <div class="flex-row">
            <svg class="icon" viewBox="0 -1 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
              <circle cx="9" cy="7" r="4"></circle>
              <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
              <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
            </svg>
            <span>人事管理</span>
          </div>
          <div class="close-btn" :class="{ 'is-collapsed': !isHRExpanded }"></div>
        </div>

        <div class="submenu-wrapper" :class="{ 'is-open': isHRExpanded }">
          <div class="submenu-content">
            <div class="submenu">
              <div v-if="isMenuVisible('5-1')" class="submenu-item" :class="{ active: isActive('5-1') }" @click.stop="handleMenuClick('5-1')">员工信息维护</div>
              <div v-if="isMenuVisible('5-2')" class="submenu-item" :class="{ active: isActive('5-2') }" @click.stop="handleMenuClick('5-2')">绩效管理</div>
            </div>
          </div>
        </div>
      </div>

      <div v-if="isMenuVisible('5-1') || isMenuVisible('5-2')" class="menu-group">
        <div class="menu-item" @click="isHRExpanded = !isHRExpanded">
          <div class="flex-row">
            <svg class="icon" viewBox="0 -1 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M10 16V9.5a1 1 0 0 1 5 0"/>
              <path d="M8 12h4"/><path d="M8 16h7"/>
              <circle cx="12" cy="12" r="10"/>
            </svg>
            <span>积分管理</span>
          </div>
          <div class="close-btn" :class="{ 'is-collapsed': !isHRExpanded }"></div>
        </div>

        <div class="submenu-wrapper" :class="{ 'is-open': isHRExpanded }">
          <div class="submenu-content">
            <div class="submenu">
              <div v-if="isMenuVisible('5-1')" class="submenu-item" :class="{ active: isActive('5-1') }" @click.stop="handleMenuClick('5-1')">员工信息维护</div>
              <div v-if="isMenuVisible('5-2')" class="submenu-item" :class="{ active: isActive('5-2') }" @click.stop="handleMenuClick('5-2')">绩效管理</div>
            </div>
          </div>
        </div>
      </div>

      <!-- 系统设置 
      <div class="menu-item" :class="{ active: isActive('10') }" @click="handleMenuClick('10')">
        <svg class="icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <path d="M9.671 4.136a2.34 2.34 0 0 1 4.659 0 2.34 2.34 0 0 0 3.319 1.915 2.34 2.34 0 0 1 2.33 4.033 2.34 2.34 0 0 0 0 3.831 2.34 2.34 0 0 1-2.33 4.033 2.34 2.34 0 0 0-3.319 1.915 2.34 2.34 0 0 1-4.659 0 2.34 2.34 0 0 0-3.32-1.915 2.34 2.34 0 0 1-2.33-4.033 2.34 2.34 0 0 0 0-3.831A2.34 2.34 0 0 1 6.35 6.051a2.34 2.34 0 0 0 3.319-1.915"/>
          <circle cx="12" cy="12" r="3"/>
        </svg>
        <span>系统设置</span>
      </div>-->
    </nav>

    <div class="footer-card">
      <div class="profile-section">
        <el-dropdown @command="handleUserCommand" trigger="click" placement="top-end">
          <div class="more-options">···</div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="profile">
                <el-icon><User /></el-icon> 个人中心
              </el-dropdown-item>
              <el-dropdown-item command="changePassword">
                <el-icon><Setting /></el-icon> 更改密码
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        
        <div class="avatar-container">
          <img src="../../assets/touxiang.jpg" class="avatar" alt="User Avatar" />
        </div>

        <div class="user-info">
          <h3 class="user-name">{{ username }}</h3>
          <p class="user-position">{{ userdept }}{{ userpos }}</p>
        </div>
      </div>

      <button class="logout-btn" @click="handleLogout">
        退出
      </button>
    </div>

    <!-- 更改密码对话框 -->
    <el-dialog
      v-model="dialogVisible"
      title="更改密码"
      width="85%"
      :style="{ maxWidth: '480px' }"
      destroy-on-close
      @close="passwordForm = { oldPassword: '', newPassword: '', confirmPassword: '' }"
    >
      <el-form label-width="35%">
        <el-row :gutter="20">
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form-item label="旧密码" required>
              <el-input 
                v-model="passwordForm.oldPassword" 
                type="password" 
                placeholder="请输入旧密码" 
                show-password
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form-item label="新密码" required>
              <el-input 
                v-model="passwordForm.newPassword" 
                type="password" 
                placeholder="请输入新密码" 
                show-password
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form-item label="确认新密码" required>
              <el-input 
                v-model="passwordForm.confirmPassword" 
                type="password" 
                placeholder="请再次输入新密码" 
                show-password
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleChangePassword">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useUserStore } from '../../stores/userStore';
import { ElMessage } from 'element-plus';
import { User, Setting, SwitchButton } from '@element-plus/icons-vue';
const router = useRouter();
const route = useRoute();

// 菜单权限列表（顺序需与二进制位一一对应）
const menuPermissions = [
  '2-1', '2-2', '2-3', '2-4', '2-5', '2-6',
  '3-1', '3-2',
  '4-1', '4-2',
  '5-1', '5-2', '5-3', '5-4',
  '6-1', '6-2', '6-3', '6-4',
  '7-1',
  '8-1', '8-2',
  '9-1', '9-2'
];

// 何杰鸣 获取用户信息
const userStore = useUserStore();
const username = computed(() => userStore.userInfo?.staffName || '用户');
const userdept = computed(() => userStore.userInfo.staffDept || '');
const userpos = computed(() => userStore.userInfo?.staffPosition || '员工');

// 何杰鸣 后续通过userdept查数据库获取supportChannel
const supportChannel = computed(() => userStore.userInfo?.supportChannels || '');
console.log('supportChannel:', supportChannel)
// 根据supportChannel二进制字符串获取可访问菜单
const getAccessibleMenus = () => {
  const bits = supportChannel.value || '';
  const result = [];
  for (let i = 0; i < bits.length && i < menuPermissions.length; i++) {
    if (bits[i] === '1') result.push(menuPermissions[i]);
  }
  return result;
};

// 检查菜单项是否可见
const isMenuVisible = (menuIndex) => {
  return getAccessibleMenus().includes(menuIndex);
};

// 定义菜单路由映射
const menuRoutes = {
  '1': '/index',
  '2-1': '/personal/approval',
  '2-2': '/personal/reimbursement',
  '2-3': '/personal/payroll',
  '2-4': '/personal/schedule',
  '2-5': '/personal/mypoints',
  '2-6': '/personal/myraffle',
  '3-1': '/finance/approval',
  '3-2': '/finance/salary',
  '4-1': '/organization/department',
  '4-2': '/organization/staffPosition',
  '5-1': '/hr/employeesInfo',
  '5-2': '/hr/E-contracts',
  '5-3': '/hr/performance',
  '5-4': '/hr/scheduling',
  '6-1': '/attendance/settings',
  '6-2': '/attendance/scheduling',
  '6-3': '/attendance/records',
  '6-4': '/attendance/reports',
  '7-1': '/warehouse/assets',
  '8-1': '/vehicle/info',
  '8-2': '/vehicle/schedule',
  '9-1': '/dormitory/info',
  '9-2': '/dormitory/allocation',
  '10': '/settings'
};

// 计算当前激活的菜单项
const activeIndex = computed(() => {
  const currentPath = route.path;
  
  for (const [index, path] of Object.entries(menuRoutes)) {
    if (path === currentPath || currentPath.startsWith(path + '/')) {
      return index;
    }
  }
  
  return '1';
});

// 检查当前路由是否在个人中心子菜单中
const isInPersonalMenu = computed(() => {
  return ['2-1', '2-2', '2-3', '2-4', '2-5', '2-6'].includes(activeIndex.value);
});

// 检查当前路由是否在组织管理子菜单中
const isInOrganizationMenu = computed(() => {
  return activeIndex.value === '4-1' || activeIndex.value === '4-2';
});

// 检查当前路由是否在组织管理子菜单中
const isInHRMenu = computed(() => {
  return activeIndex.value === '5-1' || activeIndex.value === '5-2';
});

// 检查当前路由是否在组织管理子菜单中
const isInAttendanceMenu = computed(() => {
  return activeIndex.value === '6-1' || activeIndex.value === '6-2';
});

// 为每个菜单组使用独立的展开状态
const isPersonalExpanded = ref(isInPersonalMenu.value);
const isOrganizationExpanded = ref(isInOrganizationMenu.value);
const isHRExpanded = ref(isInHRMenu.value);
const isAttendanceExpanded = ref(isInAttendanceMenu.value);

// 监听路由变化，自动展开/收起对应的子菜单
watch(() => route.path, () => {
  if (isInPersonalMenu.value) {
    isPersonalExpanded.value = true;
  }
  if (isInOrganizationMenu.value) {
    isOrganizationExpanded.value = true;
  }
  if (isInHRMenu.value) {
    isHRExpanded.value = true;
  }
  if (isInAttendanceMenu.value) {
    isAttendanceExpanded.value = true;
  }
});

// 处理菜单点击跳转
const handleMenuClick = (index) => {
  const targetPath = menuRoutes[index];
  if (targetPath && targetPath !== route.path) {
    router.push(targetPath);
  }
};

// 检查菜单项是否激活
const isActive = (index) => {
  return activeIndex.value === index;
};

// 更改密码相关
const dialogVisible = ref(false);
const passwordForm = ref({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
});

const handleUserCommand = (command) => {
  switch (command) {
    case 'profile':
      router.push('/profile');
      break;
    case 'changePassword':
      dialogVisible.value = true;
      break;
  }
};

const handleChangePassword = async () => {
  // 验证表单
  if (!passwordForm.value.oldPassword) {
    ElMessage.warning('请输入旧密码');
    return;
  }
  if (!passwordForm.value.newPassword) {
    ElMessage.warning('请输入新密码');
    return;
  }
  if (!passwordForm.value.confirmPassword) {
    ElMessage.warning('请输入确认新密码');
    return;
  }
  
  // ①判断两次输入的新密码是否一致
  if (passwordForm.value.newPassword !== passwordForm.value.confirmPassword) {
    ElMessage.error('两次输入的新密码不一致');
    return;
  }
  
  // ②新密码与旧密码不一致
  if (passwordForm.value.oldPassword === passwordForm.value.newPassword) {
    ElMessage.error('新密码不能与旧密码相同');
    return;
  }
  console.log('tel:', userStore.userInfo.tel)
  // 何杰鸣 更改密码 后端接口
  userStore.changePassword(userStore.userInfo.tel, {
    oldPassword: passwordForm.value.oldPassword,
    newPassword: passwordForm.value.newPassword
  });
    dialogVisible.value = false;
    // 清空表单
    passwordForm.value = {
      oldPassword: '',
      newPassword: '',
      confirmPassword: ''
    };
    // 跳转到登录页
    setTimeout(() => {
      router.push('/login');
    }, 1000);
};

const handleLogout = () => {
  // 何杰鸣 退出登录 需要清空所有用户信息
  userStore.clearUserInfo();
  console.log('退出登录')
  ElMessage.success('已退出登录');
  router.push('/login')
}
</script>

<style scoped>
/* 继承之前的样式 ... */
.sidebar {
  width: 260px;
  height: 100vh;
  background: #ffffff;
  display: flex;
  flex-direction: column;
  padding: 40px 0;
  box-sizing: border-box;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
  color: #333;
}

.logo-section { display: flex;  gap: 15px;  margin-bottom: 10%;  margin-left: 13.8%; flex-shrink: 0; margin-top: -4.5%;}
.logo { display: flex; align-items: center; gap: 10px; cursor: pointer; transition: opacity 0.3s; }
.logo:hover { opacity: 0.9; }
.logo-img { height: 38px; width: 38px; border-radius: 6px; }
.title { display: flex; flex-direction: column; justify-content: center; }
.dxgyzt { margin: 0; font-size: 19.5px; font-weight: 600; color: black; }
.dxgyzt-en { margin: 0; font-size: 12px; color: rgba(0,0,0,0.65); font-weight: 500; }

.menu { 
  flex: 1; 
  overflow-y: auto;
  padding: 0 27px;
  min-height: 0;
}

/* 隐藏滚动条但保持功能 */
.menu::-webkit-scrollbar {
  width: 6px;
}

.menu::-webkit-scrollbar-track {
  background: transparent;
}

.menu::-webkit-scrollbar-thumb {
  background: #e0e0e0;
  border-radius: 3px;
}

.menu::-webkit-scrollbar-thumb:hover {
  background: #c0c0c0;
}
.menu-item {
  display: flex;
  align-items: center;
  gap: 15px;
  padding: 12px 16px;
  border-radius: 14px;
  cursor: pointer;
  transition: background 0.2s;
  position: relative;
  margin-bottom: 10px;
}
.menu-item:hover { background-color: #f9f9f9; }
.menu-item.active { 
  background-color: #f0f0f0; 
  font-weight: 500;
  border-left: 3px solid #409eff;
  padding-left: 13px;
}
.flex-row { display: flex; align-items: center; gap: 15px; flex: 1;}
.icon { width: 22px; height: 22px; color: #666; }

/* --- 核心动画部分 --- */

/* X 按钮旋转动画 */
.close-btn {
  width: 18px;
  height: 18px;
  border: 1px solid #ddd;
  border-radius: 50%;
  position: relative;
  cursor: pointer;
  transition: all 0.3s ease;
  flex-shrink: 0;
}

/* 绘制 X 的两条线 */
.close-btn::before,
.close-btn::after {
  content: '';
  position: absolute;
  top: 50.5%;
  left: 50.5%;
  width: 10px; /* 线条长度 */
  height: 1px; /* 线条粗细 */
  background-color: #666;
  border-radius: 1px;
}

/* 初始状态成 X 形状 */
.close-btn::before {
  transform: translate(-50%, -50%) rotate(45deg);
}
.close-btn::after {
  transform: translate(-50%, -50%) rotate(-45deg);
}

/* 展开/收起时的状态切换 */
.close-btn.is-collapsed {
  background-color: #f0f0f0;
  transform: rotate(180deg); /* 旋转整个容器 */
}

/* 收起时让两条线垂直/水平，变成 + 号 */
.close-btn.is-collapsed::before {
  transform: translate(-50%, -50%) rotate(90deg);
}
.close-btn.is-collapsed::after {
  transform: translate(-50%, -50%) rotate(0deg);
}

/* 子菜单展开动画容器 */
.submenu-wrapper {
  display: grid;
  grid-template-rows: 0fr; /* 初始高度为 0 */
  transition: grid-template-rows 0.3s ease-out;
  overflow: hidden;
}

.submenu-wrapper.is-open {
  grid-template-rows: 1fr; /* 展开为实际内容高度 */
}

.submenu-content {
  min-height: 0; /* 配合 grid 动画必须设置 */
}

/* 树状连接线 (保持之前的逻辑) */
.submenu {
  margin-left: 36px;
  position: relative;
  padding-top: 0px;
  padding-bottom: 3px;
}

/* 左侧竖线 */
.submenu::before {
  content: "";
  position: absolute;
  left: 0;
  top: 4px;
  height: calc(100% - 4px - 8px - 15px); /* 总高度减去上下padding和最后一个子选项的一半高度 */
  width: 1.5px;
  background-color: #707070;
}

.submenu-item {
  position: relative;
  padding: 11px 8px 12px 12px;
  margin-left: 13px;
  margin-right: 16px;
  font-size: 14px;
  color: #666;
  transition: all 0.2s;
  cursor: pointer;
  border-radius: 8px;
  display: flex;
  align-items: center;
}
.submenu-item:hover { 
  color: #000;
  background-color: #f9f9f9;
}
.submenu-item.active {
  color: #409eff;
  font-weight: 500;
  background-color: #ecf5ff;
}

/* 每个子选项的横线 */
.submenu-item::before {
  content: "";
  position: absolute;
  left: -13px; /* 向左偏移，对齐到竖线位置 */
  top: 50%;
  width: 12px;
  height: 1px;
  background-color: #707070;
  height: 1.2px;
}

/* 底部样式保持不变... */
.footer-card {
  padding: 18px 10px;
  background: #f8f7f5; /* 参考图中的浅米灰底色 */
  border-radius: 24px;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  transition: all 0.3s ease;
}

/* 顶部个人信息区域 */
.profile-section {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  margin-bottom: 24px;
}

/* 三个点：绝对定位到右上角 */
.more-options {
  position: absolute;
  top: -15px;
  right: -115px;
  font-size: 21px;
  color: #949393;
  cursor: pointer;
  padding: 8px;
  line-height: 1;
  transition: color 0.2s;
}
.more-options:hover { color: #333; }

/* 头像：增加细腻的阴影 */
.avatar-container {
  margin-bottom: 16px;
}
.avatar {
  width: 72px;
  height: 72px;
  border-radius: 50%;
  object-fit: cover;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.08); /* 柔和阴影 */
}

/* 文字排版 */
.user-info {
  text-align: center;
}

.user-name {
  font-size: 18px;
  font-weight: 600;
  color: #1a1a1a;
  margin-top: -5px;
  margin-bottom: 4px;
  letter-spacing: -0.5px;

}
.user-position {
  font-size: 13.5px;
  color: #8c8c8c;
  margin: 0;
}

/* 退出登录：简约且带阴影 */
.logout-btn {
  width: 85%;
  height: 40px;
  padding: 10px 0;
  border: none;
  background: #FEFEFE;
  color: #666;
  font-size: 13px;
  font-weight: 400;
  border-radius: 12px;
  cursor: pointer;
  margin-bottom: -30px;
  margin-top: -3px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  transition: all 0.2s ease;
}

.logout-btn:hover {
  background: #fff;
  color: #ff4d4f;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
  transform: translateY(-1px);
}
</style>
