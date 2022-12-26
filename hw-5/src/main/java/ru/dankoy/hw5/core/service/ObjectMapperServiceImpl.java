package ru.dankoy.hw5.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw5.core.exceptions.MappingException;


@Service
@RequiredArgsConstructor
public class ObjectMapperServiceImpl implements ObjectMapperService {

  private final ObjectMapper objectMapper;

  @Override
  public String convertToString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new MappingException(
          String.format("Couldn't convert object of type '%s' to string", object.getClass()), e);
    }
  }

}
