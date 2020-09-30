package com.android.baihuahu.bean;

import java.io.Serializable;

/**
 * Dylan
 */
public class DeviceInfo implements Serializable{


    private int id;
    private String createTime;
    private String updateTime;
    private int projectId;
    private String name;
    private String contractNo;//合同编号
    private String category;//型号 种类?
    private int manufacturer;//厂商
    private String productionDate;
    private int leasePeriod;
    private int unitPrice;
    private Object appendPeriod;
    private int leaseCompany;//租赁公司
    private String leasePhone;
    private String leaseContact;
    private String status;
    private Object inNum;
    private String inDate;
    private Object outDate;
    private String operator;
    private String operatorPhone;
    private String operatorIdentityNo;
    private String operatorCertificateNo;
    private String remark;
    private int creator;
    private Object updator;
    private String manufacturerName;
    private int price;
    private String leaseCompanyName;
    private Object bizDeviceAttendVOList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(int manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public int getLeasePeriod() {
        return leasePeriod;
    }

    public void setLeasePeriod(int leasePeriod) {
        this.leasePeriod = leasePeriod;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Object getAppendPeriod() {
        return appendPeriod;
    }

    public void setAppendPeriod(Object appendPeriod) {
        this.appendPeriod = appendPeriod;
    }

    public int getLeaseCompany() {
        return leaseCompany;
    }

    public void setLeaseCompany(int leaseCompany) {
        this.leaseCompany = leaseCompany;
    }

    public String getLeasePhone() {
        return leasePhone;
    }

    public void setLeasePhone(String leasePhone) {
        this.leasePhone = leasePhone;
    }

    public String getLeaseContact() {
        return leaseContact;
    }

    public void setLeaseContact(String leaseContact) {
        this.leaseContact = leaseContact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getInNum() {
        return inNum;
    }

    public void setInNum(Object inNum) {
        this.inNum = inNum;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public Object getOutDate() {
        return outDate;
    }

    public void setOutDate(Object outDate) {
        this.outDate = outDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }

    public String getOperatorIdentityNo() {
        return operatorIdentityNo;
    }

    public void setOperatorIdentityNo(String operatorIdentityNo) {
        this.operatorIdentityNo = operatorIdentityNo;
    }

    public String getOperatorCertificateNo() {
        return operatorCertificateNo;
    }

    public void setOperatorCertificateNo(String operatorCertificateNo) {
        this.operatorCertificateNo = operatorCertificateNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Object getUpdator() {
        return updator;
    }

    public void setUpdator(Object updator) {
        this.updator = updator;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLeaseCompanyName() {
        return leaseCompanyName;
    }

    public void setLeaseCompanyName(String leaseCompanyName) {
        this.leaseCompanyName = leaseCompanyName;
    }

    public Object getBizDeviceAttendVOList() {
        return bizDeviceAttendVOList;
    }

    public void setBizDeviceAttendVOList(Object bizDeviceAttendVOList) {
        this.bizDeviceAttendVOList = bizDeviceAttendVOList;
    }
}
