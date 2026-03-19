package cn.yonyong.service;

import cn.yonyong.request.DepartmentRequest;
import cn.yonyong.utils.Result;

/**
 * 部门业务
 */
public interface DepartmentService {

    /** 获取部门菜单栏（SupportChannels） */
    Result<String> getMenu(Integer departmentID);

    /** 部门列表 */
    Result<?> list();

    /** 新增部门 */
    Result<?> add(DepartmentRequest body);

    /** 更新部门 */
    Result<?> update(Integer departmentID, DepartmentRequest body);

    /** 删除部门 */
    Result<?> delete(Integer departmentID);
}
