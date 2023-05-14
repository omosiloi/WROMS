package edu.scau.mis.sale.mapper;

import edu.scau.mis.sale.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderMapper
 */
@Mapper
public interface OrderMapper {

    Order selectOrderById(Long id);
    List<Order> selectOrderList(Order order);

    List<Order> selectAllOrder();

    int deleteOrderById(Long orderId);

    int deleteOrderByIds(Long[] orderIds);

    int insertOrder(Order order);

    int updateOrder(Order order);

}

