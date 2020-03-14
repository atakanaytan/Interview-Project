package wardrobe.wardrobe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "Product name is required")
    private String productName;

    @Size(max=255, message ="Description can be max 255 character long.")
    private String projectDescription;

    @NotNull(message = "Product price is required")
    private double productPrice;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", updatable = false, nullable = false)
    @JsonIgnore
    private Category category;

    @NotBlank(message = "Product quantity is required")
    private int quantity;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column (updatable = false)
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;


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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public Date getCreated_At() {

        return created_At;
    }

    public void setCreated_At(Date create_At)
    {

        this.created_At = create_At;
    }

    public Date getUpdated_At() {

        return updated_At;
    }

    public void setUpdated_At(Date update_At) {

        this.updated_At = update_At;
    }

    @PrePersist
    protected  void onCreate(){

        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate()
    {
        this.updated_At = new Date();

    }

}
