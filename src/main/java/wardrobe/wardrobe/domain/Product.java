package wardrobe.wardrobe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @NotBlank(message = "Project name is required")
    private String productName;


    @Size(max=255, message ="Description can be max 255 character long.")
    private String projectDescription;


    @NotBlank(message = "Product price is required")
    private double productPrice;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    private int quantity;


    private Date create_At;


    private Date update_At;


    public Product() {

    }


    public Long getId() {
        return Id;
    }


    public void setId(Long id) {
        Id = id;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProjectDescription() {
        return projectDescription;
    }


    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }


    public double getProductPrice() {
        return productPrice;
    }


    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }


    public int getProductStock() {
        return quantity;
    }


    public void setProductStock(int quantity) {
        this.quantity = quantity;
    }


    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column (updatable = false)
    public Date getCreate_At() {
        return create_At;
    }


    @JsonFormat(pattern = "yyyy-mm-dd")
    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }


    public Date getUpdate_At() {
        return update_At;
    }


    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }

}
