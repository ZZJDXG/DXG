import request from '../utils/request'

/**
 * 获取用户所属部门菜单
 * RESTful: GET /departments/{departmentID}/menu
 */
export const getMenu = (userdeptID) => {
  return request.get(`/departments/${userdeptID}/menu`)
}

/**
 * 查询全部部门
 * RESTful: GET /departments
 */
export const queryDepartment = () => {
  return request.get('/departments')
}

/**
 * 查询部门详情
 * RESTful: GET /departments/{departmentID}
 */
export const getDepartment = (departmentID) => {
  return request.get(`/departments/${departmentID}`)
}

/**
 * 新增部门
 * RESTful: POST /departments
 * @param {Object} data
 * @param {string} data.departmentName
 * @param {string} data.supportChannels 16位字符串
 */
export const addDepartment = (data) => {
  return request({
    url: '/departments',
    method: 'post',
    data
  })
}

/**
 * 更新部门
 * RESTful: PUT /departments/{departmentID}
 * @param {number} departmentID
 * @param {Object} data
 * @param {string} data.departmentName
 * @param {string} data.supportChannels 16位字符串
 */
export const updateDepartment = (departmentID, data) => {
  return request({
    url: `/departments/${departmentID}`,
    method: 'put',
    data
  })
}

/**
 * 删除部门
 * RESTful: DELETE /departments/{departmentID}
 * @param {number} departmentID
 */
export const deleteDepartment = (departmentID) => {
  return request({
    url: `/departments/${departmentID}`,
    method: 'delete'
  })
}
