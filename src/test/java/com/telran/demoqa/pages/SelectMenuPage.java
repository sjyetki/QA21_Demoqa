package com.telran.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectMenuPage extends PageBase{
    public SelectMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "oldSelectMenu")
    WebElement oldSelectMenu;

    public SelectMenuPage clickOnOldStyle(String text) {
        Select select = new Select(oldSelectMenu);
        select.selectByVisibleText(text);

        List<WebElement> options = select.getOptions();
        System.out.println(select.getFirstSelectedOption().getText() + " is first");
        for (int i = 0; i < options.size(); i++) {
            System.out.println(options.get(i).getText());
        }
        return this;
    }
}
