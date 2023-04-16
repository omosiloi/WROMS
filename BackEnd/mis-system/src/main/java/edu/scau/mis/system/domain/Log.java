package edu.scau.mis.system.domain;

import java.util.Date;
import java.io.Serializable;

/**
 * 操作日志(Log)实体类
 *
 * @author makejava
 * @since 2023-03-03 20:46:23
 */
public class Log implements Serializable {
    private static final long serialVersionUID = 372521365834904103L;
    /**
    * 日志主键
    */
    private Long logId;
    /**
    * 模块标题
    */
    private String title;
    /**
    * 目标对象
    */
    private String target;
    /**
    * 方法名称
    */
    private String method;
    /**
    * 业务类型
    */
    private String businessType;
    /**
    * 请求方式
    */
    private String requestMethod;
    /**
    * 请求url
    */
    private String requestUrl;
    /**
    * 请求结果编码
    */
    private Integer resultCode;
    /**
    * 请求者
    */
    private String requestBy;
    /**
    * 请求时间
    */
    private Date requestTime;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

}
