package autoservise.controller;

import java.util.List;

import autoservise.dto.mapper.OrderMapper;
import autoservise.model.Order;
import autoservise.service.impl.MasterServiceImpl;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import autoservise.dto.response.OrderResponseDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MasterControllerTest {

    @MockBean
    MasterServiceImpl masterService;
    @MockBean
    OrderMapper orderMapper;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setMockMvc() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }
    @Test
    void getMasterOrders() {
        Order orders = new Order();
        OrderResponseDto dto = new OrderResponseDto();
        Mockito.when(masterService.getMasterOrdersById(1L)).thenReturn(List.of(orders));
        Mockito.when(orderMapper.toResponseDto(orders)).thenReturn(dto);
        RestAssuredMockMvc.when()
                .get("/masters/orders/1")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(1));
    }
}