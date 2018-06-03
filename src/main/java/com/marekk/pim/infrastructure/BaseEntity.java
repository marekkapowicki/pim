package com.marekk.pim.infrastructure;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

import static com.marekk.pim.infrastructure.Uuids.uuid;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

@MappedSuperclass
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(of = "uuid")
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @Id
    @Setter
    @GeneratedValue
    Long id;

    @Version
    Long version;

    @NaturalId
    String uuid = uuid();

    @CreatedDate
    LocalDateTime creationTime;


}
