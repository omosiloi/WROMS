package edu.scau.mis.sale.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 订单表(Order)实体类
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -21891901036999507L;

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    /**
     * 关联实体
     */
    private List<OrderDetail> orderDetail;
    private Table table;

    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 餐桌ID
     */
    private Long tableId;
    /**
     * 下单时间
     */
    private Date orderTime;
    /**
     * 总价格
     */
    private BigDecimal totalPrice;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }




    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

}