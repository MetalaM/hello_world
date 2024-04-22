package org.example;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MainMenu extends BaseTest{

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

    @Test
    void shouldCheckBlog() {
        page.navigate("https://metalam.github.io/hello_world/");
        String actual = page.locator("(//nav//ul/li/a)[4]").innerText();
        assertEquals(actual, "Blog".toUpperCase());
    }
}
