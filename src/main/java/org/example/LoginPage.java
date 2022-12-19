package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    public WebDriver driver;
    private By email = By.id("email");
    private By password = By.id("pass");
    private By btnLogin = new By.ById("send2");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProfilePage login(String email, String pass) {
        driver.findElement(this.email).sendKeys(email);
        driver.findElement(this.password).sendKeys(pass);
        driver.findElement(btnLogin).click();
        return new ProfilePage(driver);
    }


}
