package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private WebDriver driver;

    private By boxContent = By.className("box-content");
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getBoxInformation() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(boxContent));
        return driver.findElement(boxContent).getAttribute("innerHTML")
                .replaceAll("<(.*?)>", "")
                .replaceAll("</(.*?)>", "")
                .replaceAll("\n", " ")
                .replaceAll(" {2}", " ");
    }
}
