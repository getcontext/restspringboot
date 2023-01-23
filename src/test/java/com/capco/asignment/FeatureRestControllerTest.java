package com.capco.asignment;

import com.capco.asignment.model.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FeatureRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Test getting all features")
    void getAll() throws Exception {

        var expectedResult = objectMapper.readValue(""/*@todo prepare sample json*/, List.class);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/features"))
                .andExpect(status().isOk()).andReturn();

        var actualResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Test getting feature with id 1")
    void getById() throws Exception {
        var expectedResult = objectMapper.readValue(""/*@todo prepare sample json*/, Feature.class);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/features/1"))
                .andExpect(status().isOk()).andReturn();

        var actualResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Feature.class);
        Assertions.assertEquals(expectedResult, actualResult);

    }

    @Test
    @DisplayName("Test getting feature with id 43 - not exist")
    void getByIdNonExistent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/features/43"))
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("Update")
    @DirtiesContext
    void update() throws Exception {

        var requestBody = ""; //@todo prepare sample json
        var responseBody = ""; //@todo prepare sample json

        var expectedResult = objectMapper.readValue(responseBody, Feature.class);

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.put("/features/1")
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();

        var actualResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Feature.class);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Create a new feature")
    @DirtiesContext
    void create() throws Exception {
        var requestBody = "";/*@todo prepare sample json*/
        var responseBody = "";/*@todo prepare sample json*/

        var expectedResult = objectMapper.readValue(responseBody, Feature.class);

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/features")
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();

        var actualResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Feature.class);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Delete an existing feature")
    @DirtiesContext
    void deleteAnExistingFeature() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/features/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Get all features with enabled true")
    void getFeaturesFilteredByEnabled() throws Exception {
        var expectedResult = objectMapper.readValue(""/*@todo prepare sample json*/, List.class);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/features?isEnabled=true"))
                .andExpect(status().isOk()).andReturn();

        var actualResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        Assertions.assertEquals(expectedResult, actualResult);
    }

}