
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { changePassword as changePasswordApi,
          login as loginApi
 } from '../api/login'
import { getMenu as getMenuApi } from '../api/department'
// 黄一1️⃣，每条注释看一下
export const useUserStore = defineStore('user', () => {
  // 用户信息
  /*
  每一行对应用户的一条属性
  id: 用户ID
  name: 用户名称
  ...
  */
  const userInfo = ref({
    staffID: '',
    staffName: '',
    password: '',
    staffDept: '',
    staffPosition: '',
    tel: '12345678900',
    supportChannels: '',
  })

  // 设置用户信息
  /*
  类似java的set方法
  传入一个对象，更新userInfo中的对应属性
   */
  const setUserInfo = (info) => {
    userInfo.value = { ...userInfo.value, ...info }
  }

  // 获取用户部门
  /**
   * 类似java的get方法，通过一个包装函数获取用户的某个属性
   * @returns 
   */
  const getUserDepartment = () => {
    return userInfo.value.staffDept
  }

  const getUserSupportChannels = (deptID) => {
    const parsedDeptID = Number(deptID)
    if (!Number.isInteger(parsedDeptID)) {
      console.log('[userStore] invalid department id for menu:', deptID)
      return ''
    }

    getMenuApi(parsedDeptID).then(res => {
      console.log('getUserSupportChannels res:', res)
      if (res.data.code === 200) {
        userInfo.value.supportChannels = res.data.data;
        ElMessage.success('获取用户支持通道成功')
      } else {
        ElMessage.error(res.data.message || '获取用户支持通道失败')
      }
    }).catch(err => {
      console.log(err)
    })
    return userInfo.value.supportChannels;
  }



  const changePassword = (tel, data) => {
    changePasswordApi(tel, {
      oldPassword: data.oldPassword,
      newPassword: data.newPassword
    }).then(res => {
      if (res.data.code === 200) {
        ElMessage.success('密码修改成功')
      } else {
        ElMessage.error(res.data.message || '密码修改失败')
      }
  }).catch(err => {
    console.log(err)
  })
}

  const login = async (data) => {
    try {
      const res = await loginApi({
        tel: data.username,
        password: data.password
      })

      if (res.data.code === 200) {
        localStorage.setItem('token', res.data.data.token)
        setUserInfo({
          staffID: res.data.data.user.staffID,
          staffName: res.data.data.user.staffName,
          password: res.data.data.password,
          staffDept: res.data.data.user.staffDept,
          staffPosition: res.data.data.user.staffPosition,
          tel: res.data.data.user.tel,
          staffBirthday: res.data.data.user.staffBirthday,
        })

        const deptID = Number(userInfo.value.staffDept)
        getUserSupportChannels(deptID)
        return true
      }

      ElMessage.error(res.data.message || '登录失败')
      return false
    } catch (error) {
      console.log(error)
      ElMessage.error(error?.message || '登录失败')
      return false
    }
  }

  // 清空用户信息
  const clearUserInfo = () => {
    userInfo.value = {
      id: '',
      name: '',
      department: 'admin',
      role: '',
      avatar: ''
    }
    localStorage.removeItem('token')
  }

  return {
    userInfo,
    setUserInfo,
    getUserDepartment,
    clearUserInfo,
    changePassword,
    login,
    getUserSupportChannels
  }
})
