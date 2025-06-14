package com.example.gameignis.controller;


import com.example.gameignis.service.impl.BaseServiceImpl;
import com.example.gameignis.util.CommonConstants;
import com.example.gameignis.dto.SampleDto;
import com.example.gameignis.exception.CommonException;
import com.example.gameignis.exception.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mugilarasan on 12/06/2025.
 */
@RestController
@RequestMapping("/base")
@Slf4j
public class BaseController {

    private final BaseServiceImpl baseService;

    public BaseController(BaseServiceImpl baseService) {
        this.baseService = baseService;
    }

    @GetMapping("/test")
    public String test(){
        return "Hello !!!";
    }

    @PostMapping("/addSampleData")
    public ResponseEntity<CommonResponse> addSampleDataToMongoDB(@RequestBody SampleDto request) {

        try {
            CommonResponse response = baseService.addSampleDataToMongoDB(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CommonException e) {
            log.error("Exception occurred while adding sample data to MongoDB: {}", e.getMessage());
            return new ResponseEntity<>(new CommonResponse(CommonConstants.ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Unexpected error occurred: {}", e.getMessage());
            return new ResponseEntity<>(new CommonResponse(CommonConstants.ERROR, "Unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

