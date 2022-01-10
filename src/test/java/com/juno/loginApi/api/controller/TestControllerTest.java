/*
package com.juno.loginApi.api.controller;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriHost = "api", uriPort = 8081)
@Transactional
class TestControllerTest {
    @Autowired
    private MockMvc mock;

    //@Test
    @DisplayName("테스트")
    void test() throws Exception{
        //given
        JSONObject json = new JSONObject();
        json.put("id", "testId1");
        json.put("pw", "testPw2");

        //when
        ResultActions act = mock.perform(MockMvcRequestBuilders.post("/v1/rest_docs").contentType(MediaType.APPLICATION_JSON).content(json.toString()));

        //then
        act.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"));

        //docs
        act.andDo(document("test-api",
                preprocessRequest(prettyPrint()),   //request json 형식으로 이쁘게 출력
                preprocessResponse(prettyPrint()),  //response json 형식으로 이쁘게 출력
                requestFields(  //request parameter
                        fieldWithPath("id").type(JsonFieldType.STRING).description("아이디"),
                        fieldWithPath("pw").type(JsonFieldType.STRING).description("비밀번호")
                ),
                responseFields( //response parameter
                        fieldWithPath("result").type(JsonFieldType.STRING).description("결과"),
                        fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드"),
                        fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메세지"),
                        fieldWithPath("data.id").type(JsonFieldType.STRING).description("아이디"),
                        fieldWithPath("data.pw").type(JsonFieldType.STRING).description("비밀번호")
                )
        ));
    }
}
*/
