package cn.yonyong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yonyong.request.CheckRequest;
import cn.yonyong.service.AttendanceRecordService;
import cn.yonyong.utils.Result;

@RestController
@RequestMapping("/attendanceRecords")
public class AttendanceRecordController {

  @Autowired
  private AttendanceRecordService attendanceRecordService;

  /**
   * 打卡（上班/下班合并）
   * POST /attendanceRecords/check
   * body: { staffID, type(0/1), locatedSpot }
   */
  @PostMapping("/check")
  public Result<?> check(@RequestBody CheckRequest body) {
    return attendanceRecordService.check(body);
  }
}