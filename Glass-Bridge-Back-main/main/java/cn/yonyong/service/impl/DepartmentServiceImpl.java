package cn.yonyong.service.impl;

import cn.yonyong.dao.DepartmentDao;
import cn.yonyong.pojo.Department;
import cn.yonyong.request.DepartmentRequest;
import cn.yonyong.service.DepartmentService;
import cn.yonyong.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Result<String> getMenu(Integer departmentID) {
        if (departmentID == null) {
            return Result.error("departmentID 不能为空");
        }
        String supportChannels = departmentDao.queryDepartmentById(departmentID);
        if (supportChannels == null) {
            return Result.error("该部门不存在");
        }
        return Result.success(supportChannels);
    }

    @Override
    public Result<?> list() {
        List<Department> list = departmentDao.queryDepartments();
        return Result.success(list);
    }

    @Override
    public Result<?> add(DepartmentRequest body) {
        if (body == null) {
            return Result.error("请求体不能为空");
        }
        String departmentName = body.getDepartmentName();
        String supportChannels = body.getSupportChannels();

        if (departmentName == null || departmentName.trim().isEmpty()) {
            return Result.error("departmentName 不能为空");
        }
        if (supportChannels == null || supportChannels.length() != 16) {
            return Result.error("supportChannels 必须是16位字符串");
        }

        String name = departmentName.trim();
        int cnt = departmentDao.countByDeptName(name);
        if (cnt > 0) {
            return Result.error("部门名称已存在，不能重复");
        }

        Department d = new Department();
        d.setDepartmentName(name);
        d.setSupportChannels(supportChannels);

        int rows = departmentDao.insertDepartment(d);
        return rows > 0 ? Result.success("新增成功") : Result.error("新增失败");
    }

    @Override
    public Result<?> update(Integer departmentID, DepartmentRequest body) {
        if (departmentID == null) {
            return Result.error("departmentID 不能为空");
        }
        if (body == null) {
            return Result.error("请求体不能为空");
        }

        String departmentName = body.getDepartmentName();
        String supportChannels = body.getSupportChannels();

        if (departmentName == null || departmentName.trim().isEmpty()) {
            return Result.error("departmentName 不能为空");
        }
        if (supportChannels == null || supportChannels.length() != 16) {
            return Result.error("supportChannels 必须是16位字符串");
        }

        String name = departmentName.trim();
        int dup = departmentDao.countByDeptNameExcludeId(name, departmentID);
        if (dup > 0) {
            return Result.error("部门名称已存在，不能重复");
        }

        Department d = new Department();
        d.setDepartmentID(departmentID);
        d.setDepartmentName(name);
        d.setSupportChannels(supportChannels);

        int rows = departmentDao.updateDepartment(d);
        return rows > 0 ? Result.success("更新成功") : Result.error("部门不存在或更新失败");
    }

    @Override
    public Result<?> delete(Integer departmentID) {
        if (departmentID == null) {
            return Result.error("departmentID 不能为空");
        }
        int rows = departmentDao.deleteDepartmentById(departmentID);
        return rows > 0 ? Result.success("删除成功") : Result.error("部门不存在或已删除");
    }
}
