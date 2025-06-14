package com.example.gameignis.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by mugilarasan on 12/06/2025.
 */
@JsonIgnoreProperties("stackTrace")
@ToString
@Data
public class CommonException extends Exception{

    private String shortMessage;
    private String detailMessage;
    private String languageCode;
    private String errorCode;
    private List<String> fieldErrors;
    private String code;
    private Integer networkHttpStatusCode;

    public CommonException() {}

    public CommonException(String shortMessage, String detailMessage, String languageCode,
                           String errorCode, List<String> fieldErrors) {
        this.detailMessage = detailMessage;
        this.shortMessage = shortMessage;
        this.languageCode = languageCode;
        this.fieldErrors = fieldErrors;
        this.errorCode = errorCode;
    }

    public CommonException(String shortMessage, String detailMessage, String languageCode,
                           String errorCode, List<String> fieldErrors, String code) {
        this.detailMessage = detailMessage;
        this.shortMessage = shortMessage;
        this.languageCode = languageCode;
        this.fieldErrors = fieldErrors;
        this.errorCode = errorCode;
        this.code = code;
    }

    public CommonException(String shortMessage, String detailMessage, String languageCode,
                           List<String> fieldErrors) {
        this.detailMessage = detailMessage;
        this.shortMessage = shortMessage;
        this.languageCode = languageCode;
        this.fieldErrors = fieldErrors;
    }

    public CommonException(String shortMessage, String detailMessage, String errorCode) {
        this.detailMessage = detailMessage;
        this.shortMessage = shortMessage;
        this.errorCode = errorCode;
    }

    public CommonException(String message) {
        super(message);
        this.detailMessage = message;
        this.shortMessage = message;
        this.errorCode = "UNKNOWN";
    }

}
