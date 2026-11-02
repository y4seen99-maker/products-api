package uk.ac.westminster.products_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class ProductHierarchyTest {

    @Test
    void summariesUseTheActualSubclass() {
        Product digital = new DigitalProduct(1L, "E-book", 29.99, "/downloads/book.pdf", 4.2);
        Product physical = new PhysicalProduct(2L, "Monitor", 249.99, 5.4, "61cm x 41cm");

        assertEquals("Digital: E-book | Download: /downloads/book.pdf", digital.getSummary());
        assertEquals("Physical: Monitor | Weight: 5.4 kg", physical.getSummary());
        assertInstanceOf(Discountable.class, digital);
        assertInstanceOf(Discountable.class, physical);
    }

    @Test
    void discountsFollowTheDifferentRules() {
        DigitalProduct digital = new DigitalProduct(1L, "E-book", 100, "/downloads/book.pdf", 4.2);
        PhysicalProduct physical = new PhysicalProduct(2L, "Monitor", 100, 5.4, "61cm x 41cm");

        digital.applyDiscount(0.30);
        physical.applyDiscount(0.30);

        assertEquals(70.0, digital.getDiscountedPrice(), 0.001);
        assertEquals(80.0, physical.getDiscountedPrice(), 0.001);
    }

    @Test
    void summariesEndpointServiceLogicUsesOneList() {
        ProductService service = new ProductService();
        assertEquals(2, service.getAllSummaries().size());
        assertEquals("Digital: Clean Code (E-book) | Download: /downloads/clean-code.pdf",
                service.getAllSummaries().get(0));
        assertEquals("Physical: 27-inch Monitor | Weight: 5.4 kg",
                service.getAllSummaries().get(1));
    }
}
