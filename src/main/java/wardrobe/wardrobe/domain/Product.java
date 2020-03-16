package wardrobe.wardrobe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String productName;

    @Size(max=255, message ="Description can be max 255 character long.")
    private String projectDescription;

    @NotNull(message = "Product price is required")
    private Double productPrice;

    @NotNull(message  = "Product quantity is required")
    private Integer quantity;

    //OneToOne with category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", updatable = false, nullable = false)
    @JsonIgnore
    private Category category;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column (updatable = false)
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;


    public Product() {

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

    public String getProjectDescription() {

        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {

        this.projectDescription = projectDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", category=" + category +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                '}';
    }
}
