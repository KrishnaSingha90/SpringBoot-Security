package com.example.SpringSecurity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
public class ProductStore {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Please provide a name")
    private String productName;

    @NotEmpty(message = "Please provide a author")
    private String productCompany;

    @NotNull(message = "Please provide a price")
    @DecimalMin("1.00")
    private BigDecimal productPrice;


    public ProductStore() {
    }

    public ProductStore(@NotEmpty(message = "Please provide a name") String productName, @NotEmpty(message = "Please provide a author") String productCompany, @NotNull(message = "Please provide a price") @DecimalMin("1.00") BigDecimal productPrice) {
        this.productName = productName;
        this.productCompany = productCompany;
        this.productPrice = productPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
