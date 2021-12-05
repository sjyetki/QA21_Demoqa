package com.telran.demoqa.tests;

import com.telran.demoqa.pages.HomePage;
import com.telran.demoqa.pages.SelectMenuPage;
import com.telran.demoqa.pages.SidePanelPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectMenuTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        new HomePage(driver).getWidgets();
        new SidePanelPage(driver).selectSelectMenu();
    }

    @Test
    public void clickOnOldStyleTest() {
        new SelectMenuPage(driver).clickOnOldStyle("Blue");
    }
}
