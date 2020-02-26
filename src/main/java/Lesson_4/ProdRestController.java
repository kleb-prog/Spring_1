package Lesson_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdRestController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/min/{d}", method = RequestMethod.GET)
    public List<Product> getMinCost(@PathVariable("d") double d) {
        return productService.getMinCost(d);
    }

    @RequestMapping(value = "/max/{d}", method = RequestMethod.GET)
    public List<Product> getMaxCost(@PathVariable("d") double d) {
        return productService.getMaxCost(d);
    }

    @RequestMapping(value = "/between/{d}/{d2}", method = RequestMethod.GET)
    public List<Product> getBetween(@PathVariable("d") double d, @PathVariable("d2") double d2) {
        return productService.getBetween(d, d2);
    }
}
