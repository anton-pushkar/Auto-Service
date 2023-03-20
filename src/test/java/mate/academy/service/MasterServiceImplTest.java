package mate.academy.service;

import mate.academy.model.Favor;
import mate.academy.model.Master;
import mate.academy.model.MasterSalaryStatus;
import mate.academy.model.Order;
import mate.academy.repository.MasterRepository;
import mate.academy.service.impl.MasterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MasterServiceImplTest {
    @InjectMocks
    private MasterServiceImpl masterService;
    @Mock
    private MasterRepository masterRepository;

    @Test
    void getMasterSalary_ok () {
        Master testMaster = new Master();
        Favor favor1 = new Favor();
        Favor favor2 = new Favor();
        Favor favor3 = new Favor();
        favor1.setCost(BigDecimal.valueOf(100.0));
        favor2.setCost(BigDecimal.valueOf(200.0));
        favor3.setCost(BigDecimal.valueOf(300.0));
        favor1.setMasterStatus(MasterSalaryStatus.UNPAID);
        favor2.setMasterStatus(MasterSalaryStatus.PAID);
        favor3.setMasterStatus(MasterSalaryStatus.UNPAID);
        List<Favor> favorList = List.of(favor1, favor2,favor3);
        Order order = new Order();
        order.setFavorList(favorList);
        testMaster.setOrdersList(List.of(order));
        Mockito.when(masterRepository.getById(1L)).thenReturn(testMaster);
        BigDecimal actual = masterService.getMasterSalaryById(1L);
        assertEquals(BigDecimal.valueOf(160.0), actual);
        assertEquals(favor1.getMasterStatus(), MasterSalaryStatus.PAID);
        assertEquals(favor2.getMasterStatus(), MasterSalaryStatus.PAID);
        assertEquals(favor3.getMasterStatus(), MasterSalaryStatus.PAID);
    }

}