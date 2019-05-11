package com.example.products.pojo;

public class History {
    private Integer id;

    private String projectId;

    private String productId;

    private String productNumber;

    private String productPrice;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber == null ? null : productNumber.trim();
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice == null ? null : productPrice.trim();
    }

    public History(Integer id, String projectId, String productId, String productNumber, String productPrice) {
        this.id = id;
        this.projectId = projectId;
        this.productId = productId;
        this.productNumber = productNumber;
        this.productPrice = productPrice;
    }

    public History() {
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", projectId='" + projectId + '\'' +
                ", productId='" + productId + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }
}