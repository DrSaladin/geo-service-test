package ru.netology.i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ru.netology.entity.Country.RUSSIA;

public class LocalizationServiceImplTest {
  private LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

  @AfterEach
  public void after_testing_nullification() {
    localizationService = null;
  }

  @Test
  public void get_message_locale() {
    String expectedResult = "Добро пожаловать";
    String actualResult = localizationService.locale(RUSSIA);

    Assertions.assertEquals(expectedResult, actualResult, "Некорректная локализация");
  }
}
