package com.juno.loginApi.api.advice;

import com.juno.loginApi.api.domain.common.CommonError;
import com.juno.loginApi.api.domain.common.CommonErrorV1;
import com.juno.loginApi.api.exception.CommonException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CommonAdvice {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity commonExceptionAdvice(CommonException ce){
        List<CommonError> errorList = new ArrayList<>();
        CommonError commonError = new CommonError(ce.getMsg());
        errorList.add(commonError);

        CommonErrorV1<List<CommonError>> result = new CommonErrorV1<>(errorList);
        return new ResponseEntity<CommonErrorV1>(result, ce.getStatus());
    }
}
