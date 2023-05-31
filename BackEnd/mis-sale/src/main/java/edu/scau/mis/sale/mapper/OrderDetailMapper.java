package edu.scau.mis.sale.mapper;

import edu.scau.mis.sale.domain.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderDetailMapper
 */
@Mapper
public interface OrderDetailMapper {

    OrderDetail selectOrderDetailById(Long id);

//    List<OrderDetail> selectOrderDetailsByOrderId(Long orderId);
//
//    List<OrderDetail> selectOrderDetailsByOrderName(String orderName);
//
//    List<Long> selectOrderDetailIdListByOrderId(Long orderId);
    List<OrderDetail> selectOrderDetailList(OrderDetail orderDetail);

    List<OrderDetail> selectAllOrderDetail();

    int deleteOrderDetailById(Long orderDetailId);

    int deleteOrderDetailByIds(Long[] orderDetailIds);

    int insertOrderDetail(OrderDetail orderDetail);

    int updateOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> selectOrderDetailByDishId(Long dishId);
    List<OrderDetail> selectOrderDetailByOrderId(Long orderId);

}

