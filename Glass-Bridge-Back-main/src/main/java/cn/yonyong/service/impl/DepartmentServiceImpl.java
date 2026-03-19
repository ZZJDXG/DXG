package cn.yonyong.service.impl;

import cn.yonyong.dao.DepartmentDao;
import cn.yonyong.pojo.Department;
import cn.yonyong.request.DepartmentRequest;
import cn.yonyong.service.DepartmentService;
import cn.yonyong.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        return Result.<String>success(supportChannels);
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

        String departmentName = trim(body.getDepartmentName());
        String supportChannels = trim(body.getSupportChannels());
        String lateStartTime = trim(body.getLateStartTime());
        Integer graceMinutes = body.getGraceMinutes();
        Integer lateEnabled = body.getLateEnabled();

        if (departmentName == null) {
            return Result.error("departmentName 不能为空");
        }
        if (supportChannels == null || supportChannels.length() != 16) {
            return Result.error("supportChannels 必须是16位字符串");
        }

        if (lateStartTime == null) {
            lateStartTime = "09:00:00";
        }
        if (graceMinutes == null) {
            graceMinutes = 0;
        }
        if (lateEnabled == null) {
            lateEnabled = 1;
        }

        if (!isValidTime(lateStartTime)) {
            return Result.error("lateStartTime 格式必须是 HH:mm:ss");
        }
        if (graceMinutes < 0 || graceMinutes > 180) {
            return Result.error("graceMinutes 必须在 0~180");
        }
        if (lateEnabled != 0 && lateEnabled != 1) {
            return Result.error("lateEnabled 只能是 0 或 1");
        }

        int cnt = departmentDao.countByDeptName(departmentName);
        if (cnt > 0) {
            return Result.error("部门名称已存在，不能重复");
        }

        Department d = new Department();
        d.setDepartmentName(departmentName);
        d.setSupportChannels(supportChannels);
        d.setLateStartTime(lateStartTime);
        d.setGraceMinutes(graceMinutes);
        d.setLateEnabled(lateEnabled);

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

        String departmentName = trim(body.getDepartmentName());
        String supportChannels = trim(body.getSupportChannels());
        String lateStartTime = trim(body.getLateStartTime());
        Integer graceMinutes = body.getGraceMinutes();
        Integer lateEnabled = body.getLateEnabled();

        if (departmentName == null) {
            return Result.error("departmentName 不能为空");
        }
        if (supportChannels == null || supportChannels.length() != 16) {
            return Result.error("supportChannels 必须是16位字符串");
        }

        Department old = departmentDao.queryDepartmentDetailById(departmentID);
        if (old == null) {
            return Result.error("部门不存在或更新失败");
        }

        if (lateStartTime == null) {
            lateStartTime = old.getLateStartTime();
        }
        if (graceMinutes == null) {
            graceMinutes = old.getGraceMinutes();
        }
        if (lateEnabled == null) {
            lateEnabled = old.getLateEnabled();
        }

        if (!isValidTime(lateStartTime)) {
            return Result.error("lateStartTime 格式必须是 HH:mm:ss");
        }
        if (graceMinutes < 0 || graceMinutes > 180) {
            return Result.error("graceMinutes 必须在 0~180");
        }
        if (lateEnabled != 0 && lateEnabled != 1) {
            return Result.error("lateEnabled 只能是 0 或 1");
        }

        int dup = departmentDao.countByDeptNameExcludeId(departmentName, departmentID);
        if (dup > 0) {
            return Result.error("部门名称已存在，不能重复");
        }

        Department d = new Department();
        d.setDepartmentID(departmentID);
        d.setDepartmentName(departmentName);
        d.setSupportChannels(supportChannels);
        d.setLateStartTime(lateStartTime);
        d.setGraceMinutes(graceMinutes);
        d.setLateEnabled(lateEnabled);

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

    private static String trim(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }

    private static boolean isValidTime(String t) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setLenient(false);
            sdf.parse(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
