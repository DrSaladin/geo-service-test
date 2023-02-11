package ru.netology;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import static ru.netology.entity.Country.RUSSIA;

public class GeoServiceImplTest {
  private GeoServiceImpl geoService;

  @BeforeEach
  public void init_test_preparation() {
    geoService = Mockito.mock(GeoServiceImpl.class);
  }

  @AfterEach
  public void after_testing_nullification() {
    geoService = null;
  }

  @Test
  public void get_ip_test() {
    Mockito.when(geoService.byIp("172.")).thenReturn(new Location("Moscow", RUSSIA, null, 0));

    Location expectedResult = new Location("Moscow", RUSSIA, null, 0);
    Location actualResult = geoService.byIp("172.");

    Assertions.assertTrue(EqualsBuilder.reflectionEquals(expectedResult, actualResult), "Актуальные и ожидаемые результаты не равны");
  }
}
