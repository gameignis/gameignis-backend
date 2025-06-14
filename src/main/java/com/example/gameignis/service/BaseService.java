package com.example.gameignis.service;

import com.example.gameignis.util.CommonConstants;
import com.example.gameignis.dto.SampleDto;
import com.example.gameignis.exception.CommonException;
import com.example.gameignis.exception.CommonResponse;
import com.example.gameignis.model.SampleModel;
import com.example.gameignis.repo.SampleRepository;
import com.example.gameignis.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by mugilarasan on 12/06/2025.
 */
@Service
@Slf4j
public class BaseService implements BaseServiceImpl {

    private final SampleRepository repo;

    public BaseService(SampleRepository repo) {
        this.repo = repo;
    }

    public CommonResponse addSampleDataToMongoDB(SampleDto request) throws CommonException {

        log.info("addSampleDataToMongoDB method Entry with request: {}", request);

        if(request == null){
            throw new CommonException(CommonConstants.NULL_REQUEST, "The request object is null", CommonConstants.ERROR_CODE);
        }

        SampleModel response = new SampleModel();
        response.setEntityId(request.getEntityId());
        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response.setCreator(request.getCreator());
        response.setChanger(request.getChanger());

        repo.save(response);

        CommonResponse res = CommonResponse.builder().result(response).build();

        log.info("addSampleDataToMongoDB method Exit with response: {}", res);
        return res;
    }
}
