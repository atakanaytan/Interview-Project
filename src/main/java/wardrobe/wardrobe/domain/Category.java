package wardrobe.wardrobe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required")
    @Column(unique = true)
    @Size(min=1, max=50)
    private String name;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column (updatable = false)
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "category", orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();


    public Category( ) {
    }

    public Long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }


    public Date getCreated_At()
    {

        return created_At;
    }

    public Date getUpdated_At()

    {

        return updated_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @PrePersist
    protected  void onCreate()
    {

        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){

        this.updated_At = new Date();
    }

}
