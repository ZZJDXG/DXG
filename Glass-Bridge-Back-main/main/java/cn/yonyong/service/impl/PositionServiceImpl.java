package cn.yonyong.service.impl;

import cn.yonyong.dao.PositionDao;
import cn.yonyong.pojo.Position;
import cn.yonyong.request.PositionRequest;
import cn.yonyong.service.PositionService;
import cn.yonyong.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

  @Autowired
  private PositionDao positionDao;

  @Override
  public Result<?> list() {
    List<Position> list = positionDao.queryPositions();
    return Result.success(list);
  }

  @Override
  public Result<?> add(PositionRequest body) {
    if (body == null) {
      return Result.error("请求体不能为空");
    }
    String positionName = body.getPositionName();
    if (positionName == null || positionName.trim().isEmpty()) {
      return Result.error("positionName 不能为空");
    }
    String name = positionName.trim();

    if (positionDao.countByPosName(name) > 0) {
      return Result.error("职级名称已存在，不能重复");
    }

    Position p = new Position();
    p.setPositionName(name);

    int rows = positionDao.insertPosition(p);
    return rows > 0 ? Result.success("新增成功") : Result.error("新增失败");
  }

  @Override
  public Result<?> update(Integer positionId, PositionRequest body) {
    if (positionId == null) {
      return Result.error("positionId 不能为空");
    }
    if (body == null) {
      return Result.error("请求体不能为空");
    }
    String positionName = body.getPositionName();
    if (positionName == null || positionName.trim().isEmpty()) {
      return Result.error("positionName 不能为空");
    }
    String name = positionName.trim();

    int dup = positionDao.countByPosNameExcludeId(name, positionId);
    if (dup > 0) {
      return Result.error("职级名称已存在，不能重复");
    }

    Position p = new Position();
    p.setPositionID(positionId);
    p.setPositionName(name);

    int rows = positionDao.updatePosition(p);
    return rows > 0 ? Result.success("更新成功") : Result.error("职级不存在或更新失败");
  }

  @Override
  public Result<?> delete(Integer positionId) {
    if (positionId == null) {
      return Result.error("positionId 不能为空");
    }
    int rows = positionDao.deletePositionById(positionId);
    return rows > 0 ? Result.success("删除成功") : Result.error("职级不存在或已删除");
  }
}
