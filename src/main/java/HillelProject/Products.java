package HillelProject;

public class Products {
    private int id;
    private String product_name;
    private double protein;
    private double carbohydrates;
    private double fats;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    @Override
    public String toString() {
        return
                "Название продукта: " + product_name + "; " +
                        "Белки: " + protein + "; " +
                        "Углеводы: " + carbohydrates + "; " +
                        "Жиры: " + fats;
    }
}
