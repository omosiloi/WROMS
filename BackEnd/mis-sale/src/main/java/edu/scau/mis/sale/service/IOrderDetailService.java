package edu.scau.mis.sale.service;

import edu.scau.mis.sale.domain.OrderDetail;

import java.util.List;

/**
 * 订单明细表(OrderDetail)表服务接口
 *
 * @author CJW
 * @since 2023-03-04 00:36:27
 */
public interface IOrderDetailService {

    OrderDetail selectOrderDetailById(Long orderDetailId);

    List<OrderDetail> selectOrderDetailList(OrderDetail orderDetail);

    List<OrderDetail> selectAllOrderDetail();

    int deleteOrderDetailById(Long orderDetailId);

    int insertOrderDetail(OrderDetail orderDetail);

    int updateOrderDetail(OrderDetail orderDetail);

}
