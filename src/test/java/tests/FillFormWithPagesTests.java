package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;

class FillFormWithPagesTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    void beforeEach() {
        registrationPage.openPage("/automation-practice-form");
        registrationPage.removeBan();
        registrationPage.checkPage("Student Registration Form");
    }

    @Test
    @Tag("FullFields")
    @Tag("automation-practice-form")
    @DisplayName("Заполнение полной формы")
    void successfulFillFormTest() {


        step("Заполнить поля", () -> {
            registrationPage.setFirstName("Alexander")
                    .setLastName("Volodin")
                    .setEmail("lex@test.ru")
                    .setGender("Male")
                    .setUserNumber("7123456789")
                    .setDateOfBirth("14", "11", "1989")
                    .setSubjects("Chemistry")
                    .setHobbies("Sports")
                    .setImage("image.jpg")
                    .setAdress("Lenina Street, 1a")
                    .setState("Rajasthan")
                    .setCity("Jaiselmer");
        });
        step("Кликнуть Submit", () -> {
            registrationPage.submit();
        });
        step("Сравнить введенные и полученные значения", () -> {
            registrationPage.checkResult("Student Name Alexander Volodin")
                    .checkResult("Student Email lex@test.ru")
                    .checkResult("Gender Male")
                    .checkResult("Mobile 7123456789")
                    .checkResult("Date of Birth 14 December,1989")
                    .checkResult("Subjects Chemistry")
                    .checkResult("Hobbies 	Sports")
                    .checkResult("Picture image.jpg")
                    .checkResult("Address Lenina Street, 1a")
                    .checkResult("State and City Rajasthan Jaiselmer");
        });
    }


    @Test
    @Tag("RequiredFields")
    @Tag("automation-practice-form")
    @DisplayName("Проверка обязательного выбора пола")
    void genderValidateFillFormTest() {

        step("Ввести имя и фамилию", () -> {
            registrationPage.setFirstName("Alexander")
                    .setLastName("Volodin");
        });
        step("Кликнуть Submit", () -> {
            registrationPage.submit();
        });
        step("Убедиться что появился красный алерт", () -> {
            registrationPage.checkValidateGender(registrationPage.redText);
        });

    }

    @Test
    @Tag("RequiredFields")
    @Tag("automation-practice-form")
    @DisplayName("Заполнение минимального обязательного количества полей")
    void minSuccessfulFillFormTest() {


        step("Ввести имя, фамилию и пол", () -> {
            registrationPage.setFirstName("Alexander")
                .setLastName("Volodin")
                    .setGender("Male");
        });
        step("Ввести номер телефона", () -> {
            registrationPage.setUserNumber("1234567890");
        });

        step("Кликнуть Submit", () -> {
            registrationPage.submit();
        });
        step("Убедиться, что форма отправилась, значения совпадают с введенными", () -> {
            registrationPage.checkResult("Student Name Alexander Volodin")
                .checkResult("Gender Male")
                .checkResult("Mobile 1234567890");
        });

    }

    @Test
    @Tag("RequiredFields")
    @Tag("PhoneNumber")
    @Tag("automation-practice-form")
    @DisplayName("Валидация формата номера телефона")
    void validatePhoneNumberTest() {


        step("Ввести имя, фамилию и пол", () -> {
            registrationPage.setFirstName("Alexander")
                .setLastName("Volodin")
                    .setGender("Male");
        });
        step("Ввести номер телефона 9 знаков", () -> {
            registrationPage.setUserNumber("123456789");
        });

        step("Кликнуть Submit", () -> {
            registrationPage.submit();
        });
        step("У поля ввода телефона появился красный алерт", () -> {
            registrationPage.checkValidatePhoneNumber(registrationPage.redText);
        });

}
}