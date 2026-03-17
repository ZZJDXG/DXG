package cn.yonyong.controller;

import cn.yonyong.request.StaffRequest;
import cn.yonyong.service.StaffService;
import cn.yonyong.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staffs")
public class StaffController {

  @Autowired
  private StaffService staffService;

  // 查询全部
  @GetMapping
  public Result<?> list() {
    return staffService.list();
  }

  // 按ID查询
  @GetMapping("/{staffID}")
  public Result<?> get(@PathVariable String staffID) {
    return staffService.getById(staffID);
  }

  // 新增
  @PostMapping
  public Result<?> add(@RequestBody StaffRequest body) {
    return staffService.add(body);
  }

  // 更新
  @PutMapping("/{staffID}")
  public Result<?> update(@PathVariable String staffID, @RequestBody StaffRequest body) {
    return staffService.update(staffID, body);
  }

  // 删除
  @DeleteMapping("/{staffID}")
  public Result<?> delete(@PathVariable String staffID) {
    return staffService.delete(staffID);
  }
}
