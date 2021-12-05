package com.telran.demoqa.pages;

import com.telran.demoqa.pages.bookstorePages.BookStorePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='category-cards']/div[.='Book Store Application']")
    WebElement bookStoreApplicationBtn;

    public BookStorePage getBookStore() {
        clickWithJSExecutor(bookStoreApplicationBtn,0,300);
        return new BookStorePage(driver);
    }

    @FindBy(xpath = "//div[@class='category-cards']/div[.='Alerts, Frame & Windows']")
    WebElement alertsFrameAndWindow;

    public SidePanelPage getAlertsFrameAndWindows() {
        clickWithJSExecutor(alertsFrameAndWindow,0,300);
        return new SidePanelPage(driver);
    }

    @FindBy(xpath = "//div[@class='category-cards']/div[.='Widgets']")
    WebElement widgets;

    public SelectMenuPage getWidgets() {
        clickWithJSExecutor(widgets,0,300);
        return new SelectMenuPage(driver);
    }
}
