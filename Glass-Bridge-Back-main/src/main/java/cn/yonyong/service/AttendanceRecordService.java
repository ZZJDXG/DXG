package cn.yonyong.service;

import cn.yonyong.request.CheckRequest;
import cn.yonyong.utils.Result;

public interface AttendanceRecordService {
  Result<?> check(CheckRequest body);
}