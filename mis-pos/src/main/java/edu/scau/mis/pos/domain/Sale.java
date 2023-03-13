package edu.scau.mis.pos.domain;

import edu.scau.mis.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale extends BaseEntity {
    private Long saleId;
    private String saleNo;
    private BigDecimal total;
    private Date saleTime;
    private String status; //相当于ooad代码Sale的isCompleted
    private List<SaleItem> saleItems = new ArrayList<>();
    private Payment payment;
    private Long paymentId;
    private String delFlag;
    // 省略setter getter和toString

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", saleNo='" + saleNo + '\'' +
                ", total=" + total +
                ", saleTime=" + saleTime +
                ", status='" + status + '\'' +
                ", saleItems=" + saleItems +
                ", payment=" + payment +
                ", paymentId=" + paymentId +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }

}
