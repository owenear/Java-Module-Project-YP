import java.util.ArrayList;

public class Calculator {

    ArrayList<Product> products;
    double productSum;
    int personCount;

    Calculator(int personCount) {
        this.personCount = personCount;
        this.productSum = 0;
        this.products = new ArrayList<>();
    }

    void addProduct(Product product) {
        products.add(product);
        productSum += product.price;
    }

    double getSumPerPerson() {
        return productSum / personCount;
    }

}
