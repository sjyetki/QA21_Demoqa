package com.telran.demoqa.pages.bookstorePages;

import com.telran.demoqa.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName")
    WebElement userName;

    @FindBy(id = "password")
    WebElement pwd;

    @FindBy(id = "login1")
    WebElement loginBtn;

    public ProfilePage login(String userN, String password) {
        type(userName,userN);
        type(pwd,password);
        loginBtn.click();
        return new ProfilePage(driver);
    }

    public LoginPage loginNegative(String userN, String password) {
        type(userName,userN);
        type(pwd,password);
        loginBtn.click();
        return this;
    }




}
