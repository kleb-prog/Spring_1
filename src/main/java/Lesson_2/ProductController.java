package Lesson_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/showForm")
    public String showProductForm(Model uiModel) {
        Product product = new Product();
        uiModel.addAttribute("product", product);
        return "prod-form";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "prod-form-result";
    }

    @RequestMapping("/showAllProducts")
    public String showAllProducts(Model uiModel) {
        ProductsContainer productsContainer = new ProductsContainer();
        productsContainer.setProducts(productService.getProductList().toArray(new Product[0]));
        uiModel.addAttribute("productContainer", productsContainer);
        return "all-products";
    }
}
