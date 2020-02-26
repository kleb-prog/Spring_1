package Lesson_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProdRepository productRepository;

    @Autowired
    public void setProductRepository(ProdRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getMinCost(double d) {
        return productRepository.findAllByCostLessThan(d);
    }

    public List<Product> getMaxCost(double d) {
        return productRepository.findAllByCostGreaterThan(d);
    }

    public List<Product> getBetween(double d, double d2) {
        return productRepository.findAllByCostBetween(d, d2);
    }
}
