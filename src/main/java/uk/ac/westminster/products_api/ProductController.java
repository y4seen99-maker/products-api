package uk.ac.westminster.products_api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) {
        return productService.findByName(name);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        // Tutorial 3 deliberately leaves the missing-product response for Lecture 6.
        return productService.getProductById(id).orElse(null);
    }

    @PostMapping
    public Product addProduct(@RequestBody Map<String, Object> body) {
        String name = (String) body.get("name");
        double price = ((Number) body.get("price")).doubleValue();

        // Keep one /products endpoint while allowing both Tutorial 4 subclasses
        // to be created from JSON without losing their extra fields.
        Product product;
        if (body.containsKey("downloadUrl")) {
            String downloadUrl = (String) body.get("downloadUrl");
            double fileSizeMB = ((Number) body.get("fileSizeMB")).doubleValue();
            product = new DigitalProduct(null, name, price, downloadUrl, fileSizeMB);
        } else if (body.containsKey("weightKg")) {
            double weightKg = ((Number) body.get("weightKg")).doubleValue();
            String dimensions = (String) body.get("dimensions");
            product = new PhysicalProduct(null, name, price, weightKg, dimensions);
        } else {
            product = new Product(null, name, price);
        }
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
