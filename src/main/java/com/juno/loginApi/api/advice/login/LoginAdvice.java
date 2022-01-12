package com.juno.loginApi.api.advice.login;

import com.juno.loginApi.api.domain.common.CommonError;
import com.juno.loginApi.api.domain.common.CommonErrorV1;
import com.juno.loginApi.api.exception.login.JoinValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class LoginAdvice {
    @ExceptionHandler(JoinValidException.class)
    public ResponseEntity joinValidException(JoinValidException jve){
        List<ObjectError> allErrors = jve.getAllErrors();

        List<CommonError> errorList = new ArrayList<>();
        if(allErrors.isEmpty()){
            CommonError commonError = new CommonError(jve.getMsg());
            errorList.add(commonError);
        }else{
            for(ObjectError oe : allErrors){
                String detail = ((FieldError)oe).getField() + " " + oe.getDefaultMessage();
                CommonError commonError = new CommonError(jve.getMsg(), detail);
                errorList.add(commonError);
            }
        }

        CommonErrorV1<List<CommonError>> result = new CommonErrorV1<>(errorList);
        return new ResponseEntity<CommonErrorV1>(result, jve.getStatus());
    }
}
