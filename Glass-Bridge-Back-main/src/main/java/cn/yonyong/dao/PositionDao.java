package cn.yonyong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yonyong.pojo.Position;

@Repository
public interface PositionDao {

  // 查询全部职级
  List<Position> queryPositions();

  // ✅ 新增职级
  int insertPosition(Position position);

  // ✅ 查重：同名
  int countByPosName(@Param("positionName") String positionName);

  // ✅ 查重：同ID
  int countByPosId(@Param("positionID") Integer positionID);

  // 删除职级
  int deletePositionById(Integer positionID);

  // 更新职级
  int updatePosition(cn.yonyong.pojo.Position position);

  // 更新查重：同名但排除当前ID
  int countByPosNameExcludeId(@Param("positionName") String positionName,
      @Param("positionID") Integer positionID);

}
