package cn.yonyong.service;

import cn.yonyong.request.PositionRequest;
import cn.yonyong.utils.Result;

/**
 * 职级业务
 */
public interface PositionService {

  /** 职级列表 */
  Result<?> list();

  /** 新增职级 */
  Result<?> add(PositionRequest body);

  /** 更新职级 */
  Result<?> update(Integer positionId, PositionRequest body);

  /** 删除职级 */
  Result<?> delete(Integer positionId);
}
