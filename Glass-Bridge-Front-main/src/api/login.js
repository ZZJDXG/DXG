import request from '../utils/request'

/**
 * 用户 RESTful API（DTO版）
 *
 * 后端对应：UserControllerRest
 * - 登录：       POST /auth/login
 * - 重置密码：   POST /users/{tel}/password/reset
 * - 修改密码：   PUT  /users/{tel}/password
 */

/** 登录
 * @param {Object} data
 * @param {string} data.usertel  手机号
 * @param {string} data.password 明文密码（后端会做 SHA256）
 */
export const login = (data) => {
  return request({
    url: '/users/login',
    method: 'post',
    data
  })
}

//一点点不符合风格的代码
/** 重置密码（密码重置为身份证后六位，按你后端逻辑）
 * @param {string} tel
 */
export const resetPassword = (tel) => {
  return request({
    url: `/users/${tel}/password/reset`,
    method: 'put'
  })
}

//一点点不符合风格的代码
/** 修改密码
 * @param {string} tel
 * @param {Object} data
 * @param {string} data.oldPassword
 * @param {string} data.newPassword
 */
export const changePassword = (tel, data) => {
  return request({
    url: `/users/${tel}/password`,
    method: 'put',
    data
  })
}
