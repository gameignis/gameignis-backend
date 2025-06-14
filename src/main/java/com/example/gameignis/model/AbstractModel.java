package com.example.gameignis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class AbstractModel implements Serializable {

    private static final long serialVersionUID = 6144168389739529112L;

    @Id
    private String id;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant changedDate;

    @CreatedBy
    private String creator;

    @LastModifiedBy
    private String changer;

    @Version
    private Integer version;

}

