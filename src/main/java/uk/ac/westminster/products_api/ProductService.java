package uk.ac.westminster.products_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public ProductService() {
        // Tutorial 4: both subclasses fit in the same List<Product>.
        products.add(new DigitalProduct(nextId++, "Clean Code (E-book)", 29.99,
                "/downloads/clean-code.pdf", 4.2));
        products.add(new PhysicalProduct(nextId++, "27-inch Monitor", 249.99,
                5.4, "61cm x 41cm x 18cm"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void deleteProduct(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> findByName(String name) {
        return products.stream()
                .filter(p -> p.getName().contains(name))
                .toList();
    }
}
