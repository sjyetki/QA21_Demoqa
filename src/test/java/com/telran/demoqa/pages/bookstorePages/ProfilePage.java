package com.telran.demoqa.pages.bookstorePages;

import com.telran.demoqa.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends PageBase {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName-value")
    WebElement user;

    @FindBy(id = "submit")
    WebElement logoutBtn;

    public ProfilePage verifyUserName(String uName) {
        if (user.getText().equalsIgnoreCase(uName)) {
            System.out.println("Correct user name: " + user.getText());
        } else {
            System.out.println("Incorrect user name: " + user.getText());
        }
        return this;
    }

    public LoginPage logout() {
        System.out.println("Let's out from profile!");
        logoutBtn.click();
        return new LoginPage(driver);
    }

    @FindBy(xpath = "//*[@id='delete-record-undefined']")
    List<WebElement> booksList;

    @FindBy(id = "closeSmallModal-ok")
    WebElement okBtn;

    public ProfilePage clickOnTrashToDeleteBook() {
        pause(500);
        booksList.get(0).click();
        pause(500);
        click(okBtn);
        pause(500);
        driver.switchTo().alert().accept();
        return this;
    }
}
