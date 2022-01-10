package com.juno.loginApi.api.controller.login;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriHost = "localhost", uriPort = 8080)
@Transactional(readOnly = true)
class LoginControllerTest {
    @Autowired
    MockMvc mock;

    @Test
    @DisplayName("회원가입 실패")
    void joinFail() throws Exception{
        //given
        //요청 데이터가 모두 비어있을 경우
        JSONObject json = new JSONObject();
        json.put("id", "");
        json.put("pw","");
        json.put("rePw","");
        json.put("email","");
        //when
        ResultActions act = mock.perform(post("/v1/join").contentType(MediaType.APPLICATION_JSON).content(json.toString()));
        //then
        act.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].msg").value("잘못된 회원가입 데이터 요청"));
    }

    @Test
    @DisplayName("회원가입 성공")
    void joinSuccess() throws Exception{
        //given
        JSONObject json = new JSONObject();
        String testId = "testId";
        json.put("id", testId);
        json.put("pw","testPw");
        json.put("rePw","testPw");
        json.put("email","testEmail@mail.com");
        //when
        ResultActions act = mock.perform(post("/v1/join").contentType(MediaType.APPLICATION_JSON).content(json.toString()));
        //then
        act.andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"))
                .andExpect(jsonPath("$.data.id").value(testId));
        //docs
        act.andDo(document("login/join",
                preprocessRequest(prettyPrint()),   //request json 형식으로 이쁘게 출력
                preprocessResponse(prettyPrint()),  //response json 형식으로 이쁘게 출력
                requestFields(
                    fieldWithPath("id").type(JsonFieldType.STRING).description("회원 아이디"),
                    fieldWithPath("pw").type(JsonFieldType.STRING).description("회원 비밀번호"),
                    fieldWithPath("rePw").type(JsonFieldType.STRING).description("비밀번호 확인"),
                    fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일")
                ),
                responseFields(
                    fieldWithPath("result").type(JsonFieldType.STRING).description("결과"),
                    fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드"),
                    fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메세지"),
                    fieldWithPath("data.id").type(JsonFieldType.STRING).description("가입 성공한 회원 아이디"),
                    fieldWithPath("data.pw").description("빈 값"),
                    fieldWithPath("data.email").description("빈 값")
                )
        ));
    }
}