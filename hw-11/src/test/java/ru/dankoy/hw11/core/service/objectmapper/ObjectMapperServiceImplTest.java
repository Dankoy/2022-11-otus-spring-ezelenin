package ru.dankoy.hw11.core.service.objectmapper;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.dankoy.hw11.core.exceptions.ObjectMapperException;


@DisplayName("Test ObjectMapperService ")
@SpringBootTest(classes = {ObjectMapper.class, ObjectMapperServiceImpl.class})
class ObjectMapperServiceImplTest {

  @MockBean
  private ObjectMapper mapper;
  @Autowired
  private ObjectMapperServiceImpl objectMapperService;


  @DisplayName("should correctly convert object to json")
  @Test
  void shouldCorrectlyMapObjectToString() throws JsonProcessingException {

    var correctMappedObj = "correct";

    given(mapper.writeValueAsString(any())).willReturn(correctMappedObj);

    String actual = objectMapperService.convertToString(new Object());

    assertThat(actual).isEqualTo(correctMappedObj);
    Mockito.verify(mapper, times(1)).writeValueAsString(any());

  }


  @DisplayName("should throw ObjectMapperException")
  @Test
  void shouldThrowException() throws JsonProcessingException {

    var objectToMap = new Object();

    var exceptionMessage = String.format("Couldn't convert object of type '%s' to string",
        objectToMap.getClass());

    Mockito.doThrow(new RuntimeException("msg")).when(mapper).writeValueAsString(any());

    assertThatThrownBy(() -> objectMapperService.convertToString(new Object()))
        .isInstanceOf(ObjectMapperException.class)
        .hasMessage(exceptionMessage);

    Mockito.verify(mapper, times(1)).writeValueAsString(any());

  }

}
