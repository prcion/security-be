package com.servustech.skeleton.features.commons;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BaseDocument implements Serializable {
    @Id
    private String id;

    @CreatedDate
    @Field(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Field(name = "updated_date")
    private LocalDateTime updatedDate;

    @CreatedBy
    @Field(name = "created_by", targetType = FieldType.OBJECT_ID)
    private String createdBy;

    @LastModifiedBy
    @Field(name = "updated_by", targetType = FieldType.OBJECT_ID)
    private String updatedBy;
}
