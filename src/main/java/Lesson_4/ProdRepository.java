package Lesson_4;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findAllByCostLessThan(double d);
    List<Product> findAllByCostGreaterThan(double d);
    List<Product> findAllByCostBetween(double d, double d2);
}
