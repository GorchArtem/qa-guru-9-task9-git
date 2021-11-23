package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1366x768";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}
