package mate.academy.service;

import mate.academy.model.*;
import mate.academy.repository.OrderRepository;
import mate.academy.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;

    @Test
    void addGoodsToOrder(){
        Order testOrder = new Order();
        testOrder.setGoodsList(new ArrayList<>());
        Goods goods1 = new Goods();
        Goods goods2 = new Goods();
        Goods goods3 = new Goods();
        goods1.setName("goods 1");
        goods2.setName("goods 2");
        goods3.setName("goods 3");
        Mockito.when(orderRepository.getById(1L)).thenReturn(testOrder);
        orderService.addGoodsToOrder(1L,goods1);
        orderService.addGoodsToOrder(1L,goods2);
        orderService.addGoodsToOrder(1L,goods3);
        assertEquals(testOrder.getGoodsList().size(), 3);
        assertEquals(testOrder.getGoodsList().get(0).getName(), "goods 1");
    }

    @Test
    void changeStatusToAccepted() {
        Order testOrder = new Order();
        Mockito.when(orderRepository.getById(1L)).thenReturn(testOrder);
        orderService.changeStatusById(1L, "ACCEPTED");
        assertEquals(testOrder.getStatus(), OrderStatus.ACCEPTED);
        assertNull(testOrder.getFinishedTime());
    }

    @Test
    void changeStatusToInProgress() {
        Order testOrder = new Order();
        Mockito.when(orderRepository.getById(1L)).thenReturn(testOrder);
        orderService.changeStatusById(1L,"IN_PROGRESS");
        assertEquals(testOrder.getStatus(), OrderStatus.IN_PROGRESS);
        assertNull(testOrder.getFinishedTime());
    }

    @Test
    void changeStatusToPaid() {
        Order testOrder = new Order();
        Mockito.when(orderRepository.getById(1L)).thenReturn(testOrder);
        orderService.changeStatusById(1L,"PAID");
        assertEquals(testOrder.getStatus(), OrderStatus.PAID);
        assertNull(testOrder.getFinishedTime());
    }

    @Test
    void changeStatusToSucFin() {
        Order testOrder = new Order();
        Mockito.when(orderRepository.getById(1L)).thenReturn(testOrder);
        orderService.changeStatusById(1L, "SUCCESSFULLY_FINISHED");
        assertEquals(testOrder.getStatus(), OrderStatus.SUCCESSFULLY_FINISHED);
        assertNotNull(testOrder.getFinishedTime());
    }

    @Test
    void changeStatusToUnsucFin() {
        Order testOrder = new Order();
        Mockito.when(orderRepository.getById(1L)).thenReturn(testOrder);
        orderService.changeStatusById(1L, "UNSUCCESSFULLY_FINISHED");
        assertEquals(testOrder.getStatus(), OrderStatus.UNSUCCESSFULLY_FINISHED);
        assertNotNull(testOrder.getFinishedTime());
    }

    @Test
    void getCostOfSuccessfullyFinishedOrder() {
        Order testOrder = new Order();
        testOrder.setStatus(OrderStatus.SUCCESSFULLY_FINISHED);
        Owner owner = new Owner();
        owner.setOrders(List.of(testOrder));
        Car car = new Car();
        car.setOwner(owner);
        testOrder.setCar(car);
        Goods goods1 = new Goods(); Goods goods2 = new Goods(); Goods goods3 = new Goods();
        Favor favor1 = new Favor(); Favor favor2 = new Favor(); Favor favor3 = new Favor();
        goods1.setCost(100.0); goods2.setCost(200.0); goods3.setCost(300.0);
        favor1.setCost(100.0); favor2.setCost(200.0); favor3.setCost(300.0);
        testOrder.setFavorList(List.of(favor1,favor2,favor3));
        testOrder.setGoodsList(List.of(goods1, goods2, goods3));
        Mockito.when(orderRepository.getById(1L)).thenReturn(testOrder);
        double actual = orderService.getOrderCostById(1L);
        assertEquals(1182, actual);
    }

    @Test
    void getCostOfUnsuccessfullyFinishedOrder() {
        Order testOrder = new Order();
        testOrder.setStatus(OrderStatus.UNSUCCESSFULLY_FINISHED);
        Owner owner = new Owner();
        owner.setOrders(List.of(testOrder));
        Car car = new Car();
        car.setOwner(owner);
        testOrder.setCar(car);
        Goods goods1 = new Goods(); Goods goods2 = new Goods(); Goods goods3 = new Goods();
        Favor favor1 = new Favor(); Favor favor2 = new Favor(); Favor favor3 = new Favor();
        goods1.setCost(100.0); goods2.setCost(200.0); goods3.setCost(300.0);
        favor1.setCost(100.0); favor2.setCost(200.0); favor3.setCost(300.0);
        testOrder.setFavorList(List.of(favor1,favor2,favor3));
        testOrder.setGoodsList(List.of(goods1, goods2, goods3));
        Mockito.when(orderRepository.getById(1L)).thenReturn(testOrder);
        double actual = orderService.getOrderCostById(1L);
        assertEquals(1682, actual);
    }
}