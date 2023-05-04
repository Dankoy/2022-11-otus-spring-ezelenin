package ru.dankoy.hw16.core.service.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import ru.dankoy.hw16.core.exceptions.ObjectMapperException;


@Service
public class ObjectMapperServiceImpl implements ObjectMapperService {

  private final ObjectMapper objectMapper;

  public ObjectMapperServiceImpl(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.objectMapper.registerModule(new JavaTimeModule());
  }

  @Override
  public String convertToString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new ObjectMapperException(
          String.format("Couldn't convert object of type '%s' to string", object.getClass()), e);
    }
  }

}
