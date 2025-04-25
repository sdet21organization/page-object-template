package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Тестовый класс с параметрами")
public class ParamTest {


    @ParameterizedTest
    @DisplayName("Тест с двумя параметрами")
    @CsvSource(value = {
            "alex, 30",
            "brian, 35",
            "charles, 40"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void testWithCsvSource(String name, int age) {

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        Allure.addAttachment("Name", name);
        Allure.addAttachment("Age", String.valueOf(age));
    }


    @ParameterizedTest
    @DisplayName("Тест с двумя параметрами")
    @CsvFileSource(resources = "/data.csv")
    void testWithCsvFileSource(String name, int age) {

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        Allure.addAttachment("Name", name);
        Allure.addAttachment("Age", String.valueOf(age));
    }

}
