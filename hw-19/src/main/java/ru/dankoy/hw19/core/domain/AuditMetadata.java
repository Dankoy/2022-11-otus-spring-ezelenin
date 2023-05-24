package ru.dankoy.hw19.core.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;


@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class AuditMetadata {

  @Setter
  @Field("dt_created")
  @CreatedDate
  private LocalDateTime dateCreated;

  @Field("dt_modified")
  @LastModifiedDate
  private LocalDateTime dateModified;

  @Setter
  @Field("created_by")
  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  @CreatedBy
  private User createdByUser;

  @Setter
  @Field("modified_by")
  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  @LastModifiedBy
  private User modifiedByUser;

}
