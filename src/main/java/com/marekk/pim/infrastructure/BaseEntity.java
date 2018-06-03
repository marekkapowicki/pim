package com.marekk.pim.infrastructure;

import lombok.EqualsAndHashCode;
import lombok.Getter;
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
public class BaseEntity {
    @Id
    @GeneratedValue
    Long id;

    @Version
    Long version;

    @Getter
    @NaturalId
    String uuid = uuid();

    @CreatedDate
    @Getter
    LocalDateTime creationTime;


}
