package mate.academy.service.impl;

import mate.academy.model.Favor;
import mate.academy.model.MasterSalaryStatus;
import mate.academy.repository.FavorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FavorServiceImplTest {
    @InjectMocks
    private FavorServiceImpl favorService;

    @Test
    void changeStatusToPaid() {
        Favor favor = new Favor();
        favor.setMasterStatus(MasterSalaryStatus.UNPAID);
        favorService.changeStatus(favor,"PAID");
        assertEquals(favor.getMasterStatus(), MasterSalaryStatus.PAID);
    }

    @Test
    void changeStatusToUnpaid() {
        Favor favor = new Favor();
        favor.setMasterStatus(MasterSalaryStatus.PAID);
        favorService.changeStatus(favor, "UNPAID");
        assertEquals(favor.getMasterStatus(), MasterSalaryStatus.UNPAID);
    }
}