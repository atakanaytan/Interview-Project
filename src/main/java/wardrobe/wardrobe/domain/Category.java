package wardrobe.wardrobe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Category name is required")
    @Column(unique = true)
    @Size(min=1, max=50)
    private String name;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column (updatable = false)
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;


    public Category( ) {
    }

    public long getId() {

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
