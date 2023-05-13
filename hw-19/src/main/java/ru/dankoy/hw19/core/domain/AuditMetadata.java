package ru.dankoy.hw19.core.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;


//todo: использовать эти поля здесь, а не в доменных классах.

@SuperBuilder
@Getter
@Setter
public class AuditMetadata {

  @Field("dt_created")
  @CreatedDate
  private LocalDateTime dateCreated;

  @Field("dt_modified")
  @LastModifiedDate
  private LocalDateTime dateModified;

  @Field("created_by")
  @CreatedBy
  private String createdByUser;

  @Field("modified_by")
  @LastModifiedBy
  private String modifiedByUser;

}
