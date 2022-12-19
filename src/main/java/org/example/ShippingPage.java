package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShippingPage {
    private WebDriver driver;
    private By firstName = By.name("firstname");
    private By lastName = By.name("lastname");
    private By company = By.name("company");
    private By address1 = By.name("street[0]");
    private By address2 = By.name("street[1]");
    private By address3 = By.name("street[2]");
    private By city = By.name("city");
    private By state = By.name("region");
    private By zip = By.name("postcode");
    private By country = By.name("country_id");
    private By telephone = By.name("telephone");

    private By btnContinue = By.cssSelector("button.action.continue.primary");

    public ShippingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setCompany(String company) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(this.company));
        driver.findElement(this.company).sendKeys(company);
    }

    public void setAddress1(String address1) {
        driver.findElement(this.address1).sendKeys(address1);
    }

    public void setAddress2(String address2) {
        driver.findElement(this.address2).sendKeys(address2);
    }

    public void setAddress3(String address3) {
        driver.findElement(this.address3).sendKeys(address3);
    }

    public void setCity(String city) {
        driver.findElement(this.city).sendKeys(city);
    }

    public void setState(String state) {
        driver.findElement(this.state).sendKeys(state);
    }

    public void setZip(String zip) {
        driver.findElement(this.zip).sendKeys(zip);
    }

    public void selectCountry(String country) {
        Select select = new Select(driver.findElement(this.country));
        select.selectByVisibleText(country);
    }

    public void setTelephone(String telephone) {
        driver.findElement(this.telephone).sendKeys(telephone);
    }

    public void setMethode(String name) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(new By.ByName(name)));
        driver.findElement(new By.ByName(name)).click();
    }

    public PaymentPage clickContinue() {
        driver.findElement(btnContinue).click();
        return new PaymentPage(driver);
    }
}
