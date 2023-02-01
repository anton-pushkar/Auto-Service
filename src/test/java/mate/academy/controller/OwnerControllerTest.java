package mate.academy.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import mate.academy.model.Order;
import mate.academy.service.impl.OwnerServiceImpl;
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

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class OwnerControllerTest {
    @MockBean
    private OwnerServiceImpl ownerService;

    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setMockMvc() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void getOrderListByUserId() {
        List<Order> orderList = List.of(new Order(), new Order(), new Order()) ;
        Mockito.when(ownerService.getOrdersByOwnerId(1L)).thenReturn(orderList);
        RestAssuredMockMvc.when()
                .get("/owners/orders/1")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3));
    }
}