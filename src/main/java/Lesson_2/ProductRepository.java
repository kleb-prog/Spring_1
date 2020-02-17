package Lesson_2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    public Product getProductById(int id) {
        return productList.stream().filter(product -> product.getId() == id).findFirst().orElseGet(Product::new);
    }
}
