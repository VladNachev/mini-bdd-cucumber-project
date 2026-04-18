package com.saucedemo.framework.pages;

import com.saucedemo.framework.components.FooterComponent;
import com.saucedemo.framework.components.HeaderComponent;
import com.saucedemo.framework.components.ProductCardComponent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private static final By PAGE_TITLE = By.className("title");
    private static final By INVENTORY_ITEMS = By.className("inventory_item");

    private final HeaderComponent header;
    private final FooterComponent footer;

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
        this.footer = new FooterComponent(driver);
    }

    public boolean isLoaded() {
        return wait.waitForUrlContains("inventory") && "Products".equals(readText(PAGE_TITLE));
    }

    public HeaderComponent header() {
        return header;
    }

    public FooterComponent footer() {
        return footer;
    }

    public int getProductCount() {
        return driver.findElements(INVENTORY_ITEMS).size();
    }

    public List<ProductCardComponent> getProducts() {
        return driver.findElements(INVENTORY_ITEMS)
                .stream()
                .map(ProductCardComponent::new)
                .toList();
    }

    public boolean hasProductNamed(String productName) {
        return getProducts()
                .stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(productName));
    }

    public ProductCardComponent getProduct(String productName) {
        return getProducts()
                .stream()
                .filter(product -> product.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productName));
    }

    public void addProductToCart(String productName) {
        clickUsingJs(By.id("add-to-cart-" + toProductSlug(productName)));
    }

    public boolean isProductAddedToCart(String productName) {
        return isVisible(By.id("remove-" + toProductSlug(productName)));
    }

    public void removeProductFromCart(String productName) {
        clickUsingJs(By.id("remove-" + toProductSlug(productName)));
    }

    public boolean canAddProductToCart(String productName) {
        return isVisible(By.id("add-to-cart-" + toProductSlug(productName)));
    }

    private String toProductSlug(String productName) {
        return productName.trim().toLowerCase().replace(" ", "-");
    }
}
