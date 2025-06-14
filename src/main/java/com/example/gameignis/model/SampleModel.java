package com.example.gameignis.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mugilarasan on 12/06/2025.
 */
@Getter
@Setter
@Document(collection = "test-config")
public class SampleModel extends AbstractModel{

    private String entityId;

    private String name;

    private String description;

}
