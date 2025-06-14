package com.example.gameignis.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Created by mugilarasan on 12/06/2025.
 */
@SuppressWarnings("deprecation")
@JsonIgnoreProperties("stackTrace")
@ToString
@Data
public class CommonResponse {

    private Object result;
    private CommonException exception;
    private Paginate pagination;

    public CommonResponse() {}

    @Builder
    public CommonResponse(Object result, CommonException exception, Paginate pagination) {
        this.result = result;
        this.exception = exception;
        this.pagination = pagination;
    }

    public CommonResponse(CommonException exception) {
        this.exception = exception;
    }

    public CommonResponse(String message, String status) {
        this.exception = new CommonException(message);
        this.result = status;
    }
}
