package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class BaseTest {
  Playwright playwright;
  Browser browser;

  // New instance for each test method.
  BrowserContext context;
  Page page;

  @BeforeClass
  void launchBrowser() {
    playwright = Playwright.create();
    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
  }

  @AfterClass
  void closeBrowser() {
    playwright.close();
  }

  @BeforeMethod
  void createContextAndPage() {
    context = browser.newContext();
    page = context.newPage();
  }

  @AfterMethod
  void closeContext() {
    context.close();
  }

  @Test
  void shouldCheckHome() {
    page.navigate("http://localhost/index.html");
    String actual = page.locator("(//nav//ul/li/a)[1]").innerText();
    assertEquals(actual, "HOME\n" + "(CURRENT)");
  }

  @Test
  void shouldCheckAbout() {
    page.navigate("http://localhost/index.html");
    String actual = page.locator("(//nav//ul/li/a)[2]").innerText();
    assertEquals(actual, "ABOUT");
  }
}

