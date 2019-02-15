package com.example.products.pojo;

public class Product {
    private Integer id;

    private String productNumber;

    private String productName;

    private String category;

    private String categoryBig;

    private String categorySmall;

    private String direction;

    private String standard;

    private String remarks;

    private String url;

    public Integer getId() {
        return id;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryBig() {
        return categoryBig;
    }

    public String getCategorySmall() {
        return categorySmall;
    }

    public String getDirection() {
        return direction;
    }

    public String getStandard() {
        return standard;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getUrl() {
        return url;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCategoryBig(String categoryBig) {
        this.categoryBig = categoryBig;
    }

    public void setCategorySmall(String categorySmall) {
        this.categorySmall = categorySmall;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productNumber='" + productNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", categoryBig='" + categoryBig + '\'' +
                ", categorySmall='" + categorySmall + '\'' +
                ", direction='" + direction + '\'' +
                ", standard='" + standard + '\'' +
                ", remarks='" + remarks + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}