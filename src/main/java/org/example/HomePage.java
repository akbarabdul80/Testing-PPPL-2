package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    public WebDriver driver;
    public By signUp = By.linkText("Create an Account");
    public By signIn = By.linkText("Sign In");

    public By mainContent = By.id("maincontent");
    public By menuUser = By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button");
    public By btnSignOut = By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[3]/a");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public CreateAccountPage clickSignUp() {
        driver.findElement(signUp).click();

        new WebDriverWait(driver, 10).until(webDriver -> webDriver.findElement(mainContent).isDisplayed());

        return new CreateAccountPage(driver);
    }

    public LoginPage clickLogin() {
        driver.findElement(signIn).click();
        new WebDriverWait(driver, 10).until(webDriver -> webDriver.findElement(mainContent).isDisplayed());
        return new LoginPage(driver);
    }

    public void clickSignOut() {
        driver.findElement(menuUser).click();
        driver.findElement(btnSignOut).click();
    }


}
