package com.juno.loginApi.api.controller;

import com.juno.loginApi.api.domain.common.CommonV1;
import com.juno.loginApi.api.exception.CommonException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/v1/test")
    public ResponseEntity test(){
        CommonV1.CommonV1Builder<Object> builder = CommonV1.builder();
        builder.result("success");
        builder.code("200");
        builder.data("뭐라도 넣어야함");
        builder.msg("성공");

        CommonV1 result = builder.build();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/v1/error")
    public ResponseEntity error(){
        CommonV1.CommonV1Builder<Object> builder = CommonV1.builder();
        builder.result("success");
        builder.code("200");
        builder.data(null);
        builder.msg("성공");

        CommonV1 result = builder.build();

        if(true) throw new CommonException("내가 만든 exception", HttpStatus.FOUND);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/v1/rest_docs")
    public ResponseEntity restDocs(@RequestBody RestDocsDto rdd){

        CommonV1.CommonV1Builder<Object> builder = CommonV1.builder();
        builder.result("success");
        builder.code("200");
        builder.data(rdd);
        builder.msg("성공");

        return ResponseEntity.status(HttpStatus.OK).body(builder.build());
    }

    @Data
    public static class RestDocsDto {
        String id;
        String pw;
    }

}
