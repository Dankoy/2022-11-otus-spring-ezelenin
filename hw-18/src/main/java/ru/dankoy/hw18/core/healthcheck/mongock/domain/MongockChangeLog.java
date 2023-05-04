package ru.dankoy.hw18.core.healthcheck.mongock.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Document("mongockChangeLog")
public class MongockChangeLog {

  @Id
  private String id;

  @Field("executionId")
  private String executionId;

  @Field("changeId")
  private String changeId;

  @Field("author")
  private String author;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Field("timestamp")
  private LocalDateTime timestamp;

  @Field("state")
  private String state;

  @Field("changeLogClass")
  private String changeLogClass;

  @Field("changeSetMethod")
  private String changeSetMethod;

  @Field("executionMillis")
  private long executionMillis;

  @Field("executionHostname")
  private String executionHostname;


}
