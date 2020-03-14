package wardrobe.wardrobe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotBlank(message = "Category name is required")
    private String categoryName;


    public Category( ) {
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return categoryName;
    }


    public void setName(String categoryName) {
        this.categoryName = categoryName;
    }

}
