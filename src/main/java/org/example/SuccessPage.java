package org.example;

import org.example.data.DataProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SuccessPage {
    WebDriver driver;
    private By textOrderNumber = By.className("order-number");
    private By orderTable = By.id("my-orders-table");
    private By tableBody = By.xpath("//*[@id=\"my-orders-table\"]/tbody");
    private By productName = By.className("product-item-name");
    private By productQty = By.className("items-qty");

    public SuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderNumber() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(textOrderNumber)).click();
    }

    public List<DataProduct> getListOrder() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(orderTable));
        List<WebElement> productList = driver.findElement(orderTable).findElements(tableBody);
        List<DataProduct> dataProducts = new ArrayList<>();
        for (WebElement product : productList) {
            DataProduct dataProduct = new DataProduct(product.findElement(productName).getText(), product.findElement(productQty).getText().replaceAll("Ordered ", ""));
            dataProducts.add(dataProduct);
        }
        return dataProducts;
    }

}
