import request from '../utils/request'

/**
 * ================================
 * Position（职级）RESTful API 封装
 * ================================
 * RESTful 风格说明：
 * --------------------------------
 * 资源名：positions
 *
 * - 查询列表： GET    /positions
 * - 查询详情： GET    /positions/{positionID}
 * - 新增职级： POST   /positions
 * - 更新职级： PUT    /positions/{positionID}
 * - 删除职级： DELETE /positions/{positionID}
 *
 * 参数传递规范：
 * --------------------------------
 * - GET 请求：参数放在 URL 中（PathVariable）
 * - POST/PUT：参数放在 JSON body 中（@RequestBody）
 * - DELETE：positionID 放在路径中（PathVariable）
 *
 * 前端统一使用 export const 箭头函数风格，便于维护。
 */

/**
 * 查询职级列表
 * GET /positions
 *
 * @returns {Promise} 返回职级数组
 */
export const queryPosition = () => {
  return request.get('/positions')
}

// /**
//  * 查询职级详情（可选）
//  * GET /positions/{positionID}
//  *
//  * ⚠️ 注意：
//  * 如果你后端暂时没有实现 queryPositionById(positionID)，
//  * 可以先不调用此接口或注释掉后端映射。
//  *
//  * @param {number} positionID 职级ID
//  * @returns {Promise}
//  */
// export const getPosition = (positionID) => {
//   return request.get(`/positions/${positionID}`)
// }

/**
 * 新增职级
 * POST /positions
 *
 * 后端接收：
 * @RequestBody PositionRequest
 *
 * @param {Object} data 请求体
 * @param {string} data.positionName 职级名称
 * @returns {Promise}
 */
export const addPosition = (data) => {
  return request({
    url: '/positions',
    method: 'post',
    data
  })
}

/**
 * 更新职级
 * PUT /positions/{positionID}
 *
 * 后端接收：
 * @PathVariable("positionID")
 * @RequestBody PositionRequest
 *
 * @param {number} positionID 职级ID
 * @param {Object} data 请求体
 * @param {string} data.positionName 职级名称
 * @returns {Promise}
 */
export const updatePosition = (positionID, data) => {
  return request({
    url: `/positions/${positionID}`,
    method: 'put',
    data
  })
}

/**
 * 删除职级
 * DELETE /positions/{positionID}
 *
 * 后端接收：
 * @PathVariable("positionID")
 *
 * @param {number} positionID 职级ID
 * @returns {Promise}
 */
export const deletePosition = (positionID) => {
  return request({
    url: `/positions/${positionID}`,
    method: 'delete'
  })
}
