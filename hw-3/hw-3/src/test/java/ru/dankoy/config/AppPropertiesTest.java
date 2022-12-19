package ru.dankoy.config;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dankoy.startup.ApplicationStartup;

@SpringBootTest
class AppPropertiesTest {

  // игнорируется бин стартапа, что бы билд проходил
  @MockBean
  private ApplicationStartup applicationStartup;

  @Autowired
  private AppProperties appProperties;

  @Test
  void testCorrectParseOfApplicationYmlToConfigClass() {

    assertThat(appProperties).hasNoNullFieldsOrProperties();
    assertThat(appProperties).isEqualTo(makeCorrectProps());

  }

  private AppProperties makeCorrectProps() {

    return new AppProperties("  %d) %s", "%d) %s", 3,
        new Locale("ru"));

  }

  @Configuration()
  @Import({AppConfigProperties.class})
  static class Config {

  }

}
