package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.RUSSIA;

public class LocalizationServiceImplTest {
  private LocalizationServiceImpl localizationService;

  @BeforeEach
  public void init_test_preparation() {
    localizationService = Mockito.mock(LocalizationServiceImpl.class);
  }

  @AfterEach
  public void after_testing_nullification() {
    localizationService = null;
  }

  @Test
  public void get_message_locale() {
    Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");

    String expectedResult = "Добро пожаловать";
    String actualResult = localizationService.locale(RUSSIA);

    Assertions.assertEquals(expectedResult, actualResult, "Некорректная локализация");
  }
}
