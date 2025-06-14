package com.example.gameignis.service.impl;

import com.example.gameignis.dto.SampleDto;
import com.example.gameignis.exception.CommonException;
import com.example.gameignis.exception.CommonResponse;

/**
 * Created by mugilarasan on 12/06/2025.
 */
public interface BaseServiceImpl {

    CommonResponse addSampleDataToMongoDB(SampleDto request) throws CommonException;
}


