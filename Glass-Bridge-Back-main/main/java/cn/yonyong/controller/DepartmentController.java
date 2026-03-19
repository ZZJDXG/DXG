package cn.yonyong.controller;

import cn.yonyong.request.DepartmentRequest;
import cn.yonyong.service.DepartmentService;
import cn.yonyong.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门 RESTful 接口
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取不同部门的菜单栏（SupportChannels）
     * GET /departments/{id}/menu
     */
    @GetMapping("/{id}/menu")
    public Result<String> getMenu(@PathVariable("id") Integer departmentID) {
        return departmentService.getMenu(departmentID);
    }

    /**
     * 查询全部部门
     * GET /departments
     */
    @GetMapping
    public Result<?> listDepartments() {
        return departmentService.list();
    }

    /**
     * 新增部门
     * POST /departments
     */
    @PostMapping
    public Result<?> addDepartment(@RequestBody DepartmentRequest body) {
        return departmentService.add(body);
    }

    /**
     * 更新部门
     * PUT /departments/{id}
     */
    @PutMapping("/{id}")
    public Result<?> updateDepartment(@PathVariable("id") Integer departmentID,
            @RequestBody DepartmentRequest body) {
        return departmentService.update(departmentID, body);
    }

    /**
     * 删除部门
     * DELETE /departments/{id}
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteDepartment(@PathVariable("id") Integer departmentID) {
        return departmentService.delete(departmentID);
    }
}
