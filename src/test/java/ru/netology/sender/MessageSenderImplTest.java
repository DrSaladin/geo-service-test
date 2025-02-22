package ru.netology.sender;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;


public class MessageSenderImplTest {
  private GeoServiceImpl geoService;
  private LocalizationServiceImpl localizationService;
  private MessageSenderImpl messageSender;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final PrintStream standardOut = System.out;

  @BeforeEach
  public void init_test_preparation() {
    geoService = Mockito.mock(GeoServiceImpl.class);
    localizationService = Mockito.mock(LocalizationServiceImpl.class);
    messageSender = new MessageSenderImpl(geoService, localizationService);
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void after_testing_nullification() {
    geoService = null;
    localizationService = null;
    messageSender = null;
    System.setOut(standardOut);
  }

  @Test
  public void get_russian_message_test() {
    String ip = "172.123.12.19";
    String expectedMessage = "Отправлено сообщение: Добро пожаловать";
    String messageError = "Возвращено некорректное сообщение";

    Map<String, String> headers = new HashMap<String, String>();
    headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
    Mockito.when(geoService.byIp(ip)).thenReturn(new Location("Moscow", RUSSIA, null, 0));
    Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");

    messageSender.send(headers);
    Assertions.assertEquals(expectedMessage, outputStreamCaptor.toString().trim(), messageError);
  }

  @Test
  public void get_english_message_test() {
    String ip = "96.44.183.149";
    String expectedMessage = "Отправлено сообщение: Welcome";
    String messageError = "Возвращено некорректное сообщение";

    Map<String, String> headers = new HashMap<String, String>();
    headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
    Mockito.when(geoService.byIp(ip)).thenReturn(new Location("New York", USA, null,  0));
    Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");

    messageSender.send(headers);
    Assertions.assertEquals(expectedMessage, outputStreamCaptor.toString().trim(), messageError);
  }
}
