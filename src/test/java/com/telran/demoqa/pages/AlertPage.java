package com.telran.demoqa.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertPage extends PageBase{
    public AlertPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "timerAlertButton")
    WebElement timerAlertBtn;

    public AlertPage clickTimerAlertBtn() {
        click(timerAlertBtn);
        WebDriverWait wait = new WebDriverWait(driver,5);
        Alert myAlert = wait.until(ExpectedConditions.alertIsPresent());
        myAlert.accept();
        return this;
    }

    @FindBy(id = "confirmButton")
    WebElement alrtBtn3;

    public AlertPage clickAlertBtn3() {
        click(alrtBtn3);
        return this;
    }

    public AlertPage clickOnConfirmButton(String text) {
        if (text != null && text.equals("Cancel")) {
            driver.switchTo().alert().dismiss();
        } else if (text != null && text.equals("Ok")) {
            driver.switchTo().alert().accept();
        }
        return this;
    }

    @FindBy(id = "confirmResult")
    WebElement confirmResult;

    public String getConfirmResult() {
        return confirmResult.getText();
    }

    @FindBy(id = "promtButton")
    WebElement promptBtn;

    public AlertPage clickMessageAlertBtn() {
        click(promptBtn);
        return this;
    }

    public AlertPage sendTextAlert(String message) {
        if (message != null) {
            driver.switchTo().alert().sendKeys(message);
        }

        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        return this;
    }

    @FindBy(id = "promptResult")
    WebElement promptResult;

    public String getConfirmMessageResult() {
        return promptResult.getText();
    }
}
