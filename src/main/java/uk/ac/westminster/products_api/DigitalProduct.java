package uk.ac.westminster.products_api;

// A DigitalProduct IS-A Product: it has all normal product details,
// plus information needed to download a file.
public class DigitalProduct extends Product implements Discountable {

    private String downloadUrl;
    private double fileSizeMB;
    private double discountedPrice;

    public DigitalProduct() {
    }

    public DigitalProduct(Long id, String name, double price,
                          String downloadUrl, double fileSizeMB) {
        super(id, name, price);
        this.downloadUrl = downloadUrl;
        this.fileSizeMB = fileSizeMB;
        this.discountedPrice = price;
    }

    @Override
    public String getSummary() {
        return "Digital: " + getName() + " | Download: " + downloadUrl;
    }

    @Override
    public void applyDiscount(double rate) {
        // A download has no shipping cost, so the full rate applies.
        this.discountedPrice = getPrice() * (1 - rate);
    }

    @Override
    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public double getFileSizeMB() {
        return fileSizeMB;
    }
}
