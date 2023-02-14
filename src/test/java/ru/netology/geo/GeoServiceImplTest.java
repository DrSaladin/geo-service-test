package ru.netology.geo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Location;

import static ru.netology.entity.Country.RUSSIA;

public class GeoServiceImplTest {
  private GeoServiceImpl geoService = new GeoServiceImpl();


  @AfterEach
  public void after_testing_nullification() {
    geoService = null;
  }

  @Test
  public void get_ip_test() {
    Location expectedResult = new Location("Moscow", RUSSIA, null, 0);
    Location actualResult = geoService.byIp("172.");

    Assertions.assertTrue(EqualsBuilder.reflectionEquals(expectedResult, actualResult),
      "Актуальные и ожидаемые результаты не равны");
  }
}
