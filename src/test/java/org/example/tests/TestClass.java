package org.example.tests;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.junit5.ReportPortalExtension;
import java.time.DayOfWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(ReportPortalExtension.class)
public class TestClass {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestClass.class);

  @TestCaseId(value = "JU5.1.0")
  @ParameterizedTest
  @ValueSource(strings = {"what", "what does it mean", "What a day!"})
  void parametrized(String candidate) {
    Assertions.assertTrue(candidate.contains("what"));
  }

  @Test
  void oneSecondTest() throws InterruptedException {
    Thread.sleep(1000);
  }

  @Test
  void twoSecondTest() throws InterruptedException {
    Thread.sleep(2000);
  }

  @Test
  void threeSecondTest() throws InterruptedException {
    Thread.sleep(3000);
  }

  @ParameterizedTest
  @ValueSource(strings = {"message1", "message2"})
  void testNotBlankParams(String input) {
    org.assertj.core.api.Assertions.assertThat(input).isBlank();
  }

  @ParameterizedTest
  @EnumSource(
      value = DayOfWeek.class,
      names = {"MONDAY", "WEDNESDAY", "FRIDAY"})
  void parameterizedTestWithEnumSource(DayOfWeek day) {
    System.out.println("parameterized-test-with-enum-source, parameter: " + day);
  }

  @Test
  void stepWithSauceLabsAttribute() {
    LOGGER.info("SauceLabs attribute with job id will be added in after method");
  }
}
