package cn.yonyong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yonyong.pojo.Department;

@Repository
public interface DepartmentDao {

    // 通过部门ID查询菜单栏（SupportChannels）
    String queryDepartmentById(Integer departmentID);

    // 通过部门ID查询完整部门信息（迟到规则会用到）
    Department queryDepartmentDetailById(Integer departmentID);

    // 查询全部部门
    List<Department> queryDepartments();

    // 新增部门
    int insertDepartment(Department department);

    // 查重
    int countByDeptName(String departmentName);

    // 删除
    int deleteDepartmentById(Integer departmentID);

    // 更新部门信息
    int updateDepartment(Department department);

    // 更新时查重：同名但排除自己
    int countByDeptNameExcludeId(@Param("departmentName") String departmentName,
            @Param("departmentID") Integer departmentID);
}
