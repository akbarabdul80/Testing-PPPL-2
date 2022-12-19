package org.example;

import org.example.data.DataProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    Actions actions;
    private By switchSize = By.cssSelector(".swatch-option.text");
    private By switchColor = By.cssSelector(".swatch-option.color");
    private By etQty = By.cssSelector(".input-text.qty");
    private By addToCart = By.cssSelector("button[title='Add to Cart']");
    private By product = By.cssSelector(".item.product.product-item");
    private By iconCart = By.cssSelector(".action.showcart");
    private By btnCheckout = By.id("top-cart-btn-checkout");
    private By titleProduct = By.className("base");
    private By cart = By.className("minicart-items");
    private By cartPorduct = By.xpath("//*[@id=\"mini-cart\"]/li");
    private By cartProductName = By.className("product-item-name");
    private By cartProductQty = By.className("item-qty");
    private By counterCart = By.className("counter-number");

    private By addToWishlist = By.className("towishlist");

    private By messageSuccess = By.className("message-success");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public void setSize(Integer size) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(switchSize));
        driver.findElements(switchSize).get(size).click();
    }

    public void setColor(Integer color) {
        driver.findElements(switchColor).get(color).click();
    }

    public void setQty(Integer qty) {
        driver.findElement(etQty).clear();
        driver.findElement(etQty).sendKeys(qty.toString());
    }

    public void addToCart(String qty) {
        driver.findElement(addToCart).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(counterCart, qty));
    }

    public void clickProduct(Integer index) {
        driver.findElements(product).get(index).click();
    }

    public void clickIconCart() {
        driver.findElement(iconCart).click();
    }

    public void clickCheckout() {
        driver.findElement(btnCheckout).click();
    }

    public String getTitle() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(titleProduct));
        return driver.findElement(titleProduct).getText();
    }


    public String getQty() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(etQty));
        return driver.findElement(etQty).getAttribute("value");
    }

    public void addToWishlist() {
        driver.findElement(addToWishlist).click();
    }

    public String getMessageSuccess() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(messageSuccess));
        return driver.findElement(messageSuccess).getText();
    }


    public List<DataProduct> getCart() {
        WebElement cart = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(this.cart));
        List<WebElement> productList = cart.findElements(cartPorduct);
        List<DataProduct> dataProducts = new ArrayList<>();
        for (WebElement product : productList) {
            DataProduct dataProduct = new DataProduct(product.findElement(cartProductName).getText(), product.findElement(cartProductQty).getAttribute("value"));
            dataProducts.add(dataProduct);
        }
        Collections.reverse(dataProducts);
        return dataProducts;
    }
}
