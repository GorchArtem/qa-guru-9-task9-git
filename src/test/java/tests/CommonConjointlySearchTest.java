package tests;


import com.codeborne.selenide.Condition;
import helpers.SearchQuery;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static helpers.Constants.*;


public class CommonConjointlySearchTest extends TestBase {

    //@value source
    @ParameterizedTest(name = "Search with text = {0} and checked it on the page")
    @ValueSource(strings = {"MAXDIFF", "DIY"})
    public void searchWithValueSource(String searchQuery) {
        open(SEARCH_URL);
        $(SEARCH_SELECTOR).setValue(searchQuery).pressEnter();
        $$(RESULT_SELECTOR).find(Condition.text(searchQuery)).shouldBe(Condition.visible);
    }

    //@EnumSource
    @ParameterizedTest(name = "Search with text = {0} and checked it on the page")
    @EnumSource(SearchQuery.class)
    public void searchWithEnumSource(SearchQuery searchQuery) {
        open(SEARCH_URL);
        $(SEARCH_SELECTOR).setValue(searchQuery.name()).pressEnter();
        $$(RESULT_SELECTOR).find(Condition.text(searchQuery.name())).shouldBe(Condition.visible);
    }

    //@MethodSource
    static Stream<Arguments> stringProvider() {
        return Stream.of(
                Arguments.of("Generic conjoint", "conjoint analysis"),
                Arguments.of("Brand specific conjoint", "Relative performance of brands")
        );
    }

    @ParameterizedTest(name = "Search with text = {0} and checked it on the page")
    @MethodSource("stringProvider")
    public void searchWithMethodSource(String searchQuery, String searchResult) {
        open(SEARCH_URL);
        $(SEARCH_SELECTOR).setValue(searchQuery).pressEnter();
        $$(RESULT_SELECTOR).find(Condition.text(searchResult)).shouldBe(Condition.visible);
    }

    //@CsvSource
    @ParameterizedTest(name = "Search with text = {0} and checked it on the page = {1}")
    @CsvSource(value = {
            "Van Westendorp | Van Westendorp Price Sensitivity Meter",
            "Gabor-Granger  | Gabor-Granger Model"},
            delimiter = '|')
    public void searchWithCsvSource(String searchQuery, String searchResult) {
        open(SEARCH_URL);
        $(SEARCH_SELECTOR).setValue(searchQuery).pressEnter();
        $$(RESULT_SELECTOR).find(Condition.text(searchResult)).shouldBe(Condition.visible);
    }
}
