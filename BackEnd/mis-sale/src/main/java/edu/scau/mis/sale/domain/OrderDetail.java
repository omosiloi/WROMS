package edu.scau.mis.sale.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * 订单明细表(OrderDetail)实体类
 */
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = -31891901036999507L;
    private Integer month;
    private BigDecimal monthlySales;
    private String name;
    private Integer value;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(BigDecimal monthlySales) {
        this.monthlySales = monthlySales;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


    private Dish dish;
    private Long orderDetailId;
    private Long orderId;
    private Long dishId;
    private int quantity;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}