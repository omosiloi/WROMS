package edu.scau.mis.sale.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * 餐桌表(Table)实体类
 */
public class Table implements Serializable {
    private static final long serialVersionUID = -11891901036999507L;

    /**
     * 餐桌ID
     */
    private Long tableId;

    /**
     * 餐桌名称
     */
    private String tableName;
    /**
     * 容量
     */
    private Long tableCapacity;
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


    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getTableCapacity() {
        return tableCapacity;
    }

    public void setTableCapacity(Long tableCapacity) {
        this.tableCapacity = tableCapacity;
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

}
