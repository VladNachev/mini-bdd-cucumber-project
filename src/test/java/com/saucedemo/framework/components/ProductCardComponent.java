package com.saucedemo.framework.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductCardComponent {

    private final WebElement rootElement;

    public ProductCardComponent(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public String getName() {
        return rootElement.findElement(By.className("inventory_item_name")).getText().trim();
    }

    public String getDescription() {
        return rootElement.findElement(By.className("inventory_item_desc")).getText().trim();
    }

    public String getPrice() {
        return rootElement.findElement(By.className("inventory_item_price")).getText().trim();
    }

    public void addToCart() {
        rootElement.findElement(By.tagName("button")).click();
    }
}
