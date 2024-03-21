package com.skill.springrestdocsdemo.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.templates.TemplateFormat;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest
@RunWith(SpringRunner.class)
public class DemoControllerTests {

    private static final String BASE_URL = "/api/v1/demo";

    @Autowired
    protected WebApplicationContext webappContext;

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp() throws Exception {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webappContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation)
                        //Remove comment if you want to change the format to markdown
                        //.snippets().withTemplateFormat(TemplateFormats.markdown())
                )
                .build();
    }

    @Test
    public void create_shouldReturnNewId_success() throws Exception {

        this.mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"name\",\"description\":\"description\"}"))
                .andExpect(status().isOk())
                .andDo(
                        document("create",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                PayloadDocumentation.relaxedRequestFields(
                                        fieldWithPath("name")
                                                .description("Name of the demo")
                                                .type(JsonFieldType.STRING),
                                        fieldWithPath("description")
                                                .description("Description of the demo")
                                                .type(JsonFieldType.STRING)
                                )
                        )
                );
    }

    @Test
    public void get_shouldReturnDemoView_success() throws Exception {

        String id = "demo-test-id";

        this.mockMvc.perform(get(BASE_URL + "/{id}", id))
                .andExpect(status().isOk())
                .andDo(
                        document("get",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("Id of the demo")
                                ),
                                PayloadDocumentation.relaxedResponseFields(
                                        fieldWithPath("id")
                                                .description("Id of the demo")
                                                .type(JsonFieldType.STRING),
                                        fieldWithPath("name")
                                                .description("Name of the demo")
                                                .type(JsonFieldType.STRING),
                                        fieldWithPath("description").description("Description of the demo")
                                                .type(JsonFieldType.STRING)
                                )
                        )
                );
    }

    @Test
    public void update_shouldUpdateRecordById_success() throws Exception {

        String id = "demo-test-id";

        this.mockMvc.perform(put(BASE_URL + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"name\",\"description\":\"description\"}"))
                .andExpect(status().isOk())
                .andDo(
                        document("update",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                RequestDocumentation.relaxedPathParameters(
                                        RequestDocumentation.parameterWithName("id").description("Id of the demo")
                                ),
                                PayloadDocumentation.relaxedRequestFields(
                                        fieldWithPath("name")
                                                .description("Name of the demo")
                                                .type(JsonFieldType.STRING),
                                        fieldWithPath("description")
                                                .description("Description of the demo")
                                                .type(JsonFieldType.STRING)
                                )
                        )
                );
    }

    @Test
    public void delete_shouldDeleteRecordById_success() throws Exception {

        String id = "demo-test-id";

        this.mockMvc.perform(delete(BASE_URL + "/{id}", id))
                .andExpect(status().isOk())
                .andDo(
                        document("delete",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                RequestDocumentation.relaxedPathParameters(
                                        RequestDocumentation.parameterWithName("id").description("Id of the demo")
                                )
                        )
                );
    }
}
