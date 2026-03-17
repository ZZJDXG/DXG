package cn.yonyong.controller;

import cn.yonyong.request.PositionRequest;
import cn.yonyong.service.PositionService;
import cn.yonyong.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 职级 RESTful 接口
 */
@RestController
@RequestMapping("/positions")
public class PositionController {

  @Autowired
  private PositionService positionService;

  /**
   * 查询全部职级
   * GET /positions
   */
  @GetMapping
  public Result<?> listPositions() {
    return positionService.list();
  }

  /**
   * 新增职级
   * POST /positions
   */
  @PostMapping
  public Result<?> addPosition(@RequestBody PositionRequest body) {
    return positionService.add(body);
  }

  /**
   * 更新职级
   * PUT /positions/{id}
   */
  @PutMapping("/{id}")
  public Result<?> updatePosition(@PathVariable("id") Integer positionId,
      @RequestBody PositionRequest body) {
    return positionService.update(positionId, body);
  }

  /**
   * 删除职级
   * DELETE /positions/{id}
   */
  @DeleteMapping("/{id}")
  public Result<?> deletePosition(@PathVariable("id") Integer positionId) {
    return positionService.delete(positionId);
  }
}
