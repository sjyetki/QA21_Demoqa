package com.telran.demoqa.pages.bookstorePages;

import com.telran.demoqa.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookStorePage extends PageBase {
    public BookStorePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "login")
    WebElement loginBtn;

    public LoginPage getLoginPage() {
        click(loginBtn);
        return new LoginPage(driver);
    }

    @FindBy(id = "searchBox")
    WebElement seachInput;

    public BookStorePage typeInSearchFieldInput(String text) {
        type(seachInput, text);
        return this;
    }

    public String takeNameOfBook() {
        return driver.findElement(By.xpath("//span[@class='mr-2']/a")).getText();
    }

    public String verifyEmptyField() {
        return driver.findElement(By.cssSelector(".rt-tr-group:nth-child(1) .rt-td:nth-child(2)"))
                .getText();
    }
    
    @FindBy (xpath = "//span[@class='mr-2']/a")
    WebElement nameOfBook;

    public BookStorePage clickByFirstBook() {
        click(nameOfBook);
        return this;
    }

    @FindBy(css = ".text-right.fullButton")
    WebElement addToCollectionButton;

    public BookStorePage addToYourCollection() {
        clickWithJSExecutor(addToCollectionButton,0,400);
        pause(3000);
        driver.switchTo().alert().accept();
        return this;
    }
}
