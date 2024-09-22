package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement calendarInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesInput = $("#hobbiesWrapper");
    private final SelenideElement imageInput = $("#uploadPicture");
    private final SelenideElement adressInput = $("#currentAddress");
    private final SelenideElement selectState = $("#stateCity-wrapper");
    private final SelenideElement selectCity = $("#stateCity-wrapper");
    private final SelenideElement submit = $("#submit");
    private final SelenideElement genderColor = $("[for=gender-radio-1]");
    public String redText = "rgba(220, 53, 69, 1)";
    CalendarComponent calendarComponent = new CalendarComponent();
    ResultComponent resultComponent = new ResultComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;
    }

    public RegistrationPage removeBan() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }


    public RegistrationPage setHobbies(String value) {
        hobbiesInput.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setImage(String value) {
        imageInput.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage setAdress(String value) {
        adressInput.setValue(value);

        return this;
    }


    public RegistrationPage setState(String value) {
        selectState.$(byText("Select State")).click();
        selectState.$(byText(value)).scrollTo().click();

        return this;
    }


    public RegistrationPage setCity(String value) {
        selectCity.$(byText("Select City")).click();
        selectCity.$(byText(value)).scrollTo().click();

        return this;
    }


    public RegistrationPage submit() {
        submit.scrollTo().click();

        return this;
    }

    public RegistrationPage checkResult(String valueResult) {
        resultComponent.checkTable(valueResult);

        return this;
    }

    public RegistrationPage checkValidateGender(String value) {
        genderColor.shouldHave(cssValue("color", redText));

        return this;
    }

    public RegistrationPage checkValidatePhoneNumber(String value) {
        userNumberInput.shouldHave(cssValue("border-bottom-color", redText));

        return this;
    }
}