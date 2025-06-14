package com.example.gameignis.repo;

import com.example.gameignis.model.SampleModel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mugilarasan on 12/06/2025.
 */
public interface SampleRepository extends MongoRepository<SampleModel, String> {

}


