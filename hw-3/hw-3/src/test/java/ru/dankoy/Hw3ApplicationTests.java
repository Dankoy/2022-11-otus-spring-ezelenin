package ru.dankoy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.dankoy.startup.ApplicationStartup;

@SpringBootTest
class Hw3ApplicationTests {

  // игнорируется бин стартапа, что бы билд проходил
  @MockBean
  private ApplicationStartup applicationStartup;

  @Test
  void contextLoads() {
  }

}
