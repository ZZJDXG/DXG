package cn.yonyong.service;

import cn.yonyong.request.StaffRequest;
import cn.yonyong.utils.Result;

public interface StaffService {
  Result<?> list();

  Result<?> getById(String staffID);

  Result<?> add(StaffRequest body);

  Result<?> update(String staffID, StaffRequest body);

  Result<?> delete(String staffID);
}
