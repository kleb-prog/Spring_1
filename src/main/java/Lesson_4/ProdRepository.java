package Lesson_4;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAll();
}
