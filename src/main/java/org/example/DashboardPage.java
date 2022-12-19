package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private Actions actions;
    private By product = By.className("product-item-info");

    private By gear = By.xpath("//*[@id=\"ui-id-6\"]");

    private By bags = By.xpath("//*[@id=\"ui-id-25\"]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public ProductPage clickProduct(Integer index) {
        driver.findElements(product).get(index).click();
        return new ProductPage(driver);
    }

    public void gotoDashboard() {
        driver.get("https://magento.softwaretestingboard.com/");
    }

    public ProductPage clickBagsWithHover() {
        WebElement menMenuElement = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(gear));
        actions.moveToElement(menMenuElement).perform();
        driver.findElement(bags).click();
        return new ProductPage(driver);
    }
}
