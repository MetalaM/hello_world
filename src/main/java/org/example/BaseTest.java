package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class BaseTest {
  Playwright playwright;
  Browser browser;
  BrowserContext context;
  Page page;
  Properties properties = new Properties();
  FileInputStream fileInputStream = null;
  BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50);

  @BeforeClass
  void launchBrowser() {
    try {
      fileInputStream = new FileInputStream("src/main/resources/properties.properties");
      properties.load(fileInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fileInputStream != null) {
        try {
          fileInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      playwright = Playwright.create();
      browser = playwright.chromium().launch(properties.getProperty("headlessmode").equals("true")?null:launchOptions);
    }
  }

  @AfterClass
  void closeBrowser() {
    playwright.close();
  }

  @BeforeMethod
  void createContextAndPage() {
    if(properties.getProperty("headlessmode").equals("false")){
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      context = browser.newContext(properties.getProperty("headlessmode").equals("true") ?
              null : new Browser.NewContextOptions().setViewportSize((int)screenSize.getWidth(), (int)screenSize.getHeight()));
    }
    else context = browser.newContext();
    page = context.newPage();
  }

  @AfterMethod
  void closeContext() {
    context.close();
  }

  @Test
  void shouldCheckHome() {
    page.navigate("https://metalam.github.io/hello_world/");
    String actual = page.locator("(//nav//ul/li/a)[1]").innerText();
    assertEquals(actual, "HOME\n" + "(CURRENT)");
  }

  @Test
  void shouldCheckAbout() {
    page.navigate("https://metalam.github.io/hello_world/");
    String actual = page.locator("(//nav//ul/li/a)[2]").innerText();
    assertEquals(actual, "ABOUT");
  }

  @Test
  void shouldCheckClasses() {
    page.navigate("https://metalam.github.io/hello_world/");
    String actual = page.locator("(//nav//ul/li/a)[3]").innerText();
    assertEquals(actual, "Classes".toUpperCase());
  }
}

