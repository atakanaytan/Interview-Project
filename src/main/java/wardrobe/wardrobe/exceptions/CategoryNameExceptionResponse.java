package wardrobe.wardrobe.exceptions;


public class CategoryNameExceptionResponse {

    private String categoryName;

    public CategoryNameExceptionResponse(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
