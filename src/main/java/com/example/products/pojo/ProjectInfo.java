package com.example.products.pojo;

import java.util.Date;

public class ProjectInfo {
    private Integer id;

    private String projectId;

    private String projectName;

    private String author;

    private String authorPhone;

    private String customer;

    private Date createTime;

    private String totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getAuthorPhone() {
        return authorPhone;
    }

    public void setAuthorPhone(String authorPhone) {
        this.authorPhone = authorPhone == null ? null : authorPhone.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice == null ? null : totalPrice.trim();
    }

    public ProjectInfo(Integer id, String projectId, String projectName, String author, String authorPhone, String customer, Date createTime, String totalPrice) {
        this.id = id;
        this.projectId = projectId;
        this.projectName = projectName;
        this.author = author;
        this.authorPhone = authorPhone;
        this.customer = customer;
        this.createTime = createTime;
        this.totalPrice = totalPrice;
    }

    public ProjectInfo() {
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "id=" + id +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", author='" + author + '\'' +
                ", authorPhone='" + authorPhone + '\'' +
                ", customer='" + customer + '\'' +
                ", createTime=" + createTime +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }
}