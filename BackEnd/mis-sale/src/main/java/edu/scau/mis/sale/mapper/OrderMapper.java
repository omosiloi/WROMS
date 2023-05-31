package edu.scau.mis.sale.mapper;

import edu.scau.mis.sale.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * OrderMapper
 */
@Mapper
public interface OrderMapper {

    Order selectOrderById(Long id);
    List<Order> selectOrderList(Order order);
    List<Order> selectOrderByTableIdAndStatus(@Param("tableId") Long tableId, @Param("status") String status);
    List<Order> selectAllOrder();

    int deleteOrderById(Long orderId);

    int deleteOrderByIds(Long[] orderIds);

    int insertOrder(Order order);

    int updateOrder(Order order);

}

