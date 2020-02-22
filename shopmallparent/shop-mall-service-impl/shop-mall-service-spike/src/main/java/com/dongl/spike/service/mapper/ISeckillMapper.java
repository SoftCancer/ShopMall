package com.dongl.spike.service.mapper;

import com.dongl.spike.service.mapper.entity.SeckillEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/22 18:15
 * @Version: 1.0
 */
public interface ISeckillMapper {

    @Select("select seckill_id as seckillId ,name,inventory ,start_time as startTime,end_time as endTime," +
            " create_time as createTime , version  from meite_seckill where seckill_id=#{seckillId};")
    SeckillEntity findBySeckillId(Long seckillId);

    //INSERT INTO `shopmall`.`meite_seckill` (`seckill_id`, `name`, `inventory`, `start_time`, `end_time`,
    // `create_time`, `version`) VALUES ('1', 'iPhone XS', '100', '2020-02-22 17:33:08', '2020-02-29 17:33:12', '2020-02-22 17:33:17', '0');

    @Update("update meite_seckill set inventory=inventory-1, version=version+1 where  seckill_id=#{seckillId} and inventory>0 ;")
    int inventoryDeduction(@Param("seckillId") Long seckillId);

    @Update("update meite_seckill set inventory=inventory-1, version=version+1 where  seckill_id=#{seckillId} and inventory>0 and version=#{version};")
    int inventoryDeductionTwo(@Param("seckillId") Long seckillId ,@Param("version") Long version);

    @Update("update meite_seckill set inventory=inventory-1, version=version+1 where  seckill_id=#{seckillId} ; ")
    int modifyInventory(@Param("seckillId") Long seckillId, @Param("version") Long version);
}
