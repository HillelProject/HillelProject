package HillelProject;

import java.util.Objects;

/* Класс Products, создан для переменных из Таблицы Базы Данных, в случае, если продукт будет в базе
то переменные берут значение этих продуктов.
*/
public class Products {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Products)) return false;
        Products products = (Products) o;
        return Double.compare(products.calories, calories) == 0 && Double.compare(products.protein, protein) == 0 && Double.compare(products.carbohydrates, carbohydrates) == 0 && Double.compare(products.fats, fats) == 0 && product_name.equals(products.product_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calories, product_name, protein, carbohydrates, fats);
    }

    private double calories;
    private String product_name;
    private double protein;
    private double carbohydrates;
    private double fats;


    public double getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
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
                        "Жиры: " + fats + "; " +
                        "Калории: " + calories + "; ";
    }
}
