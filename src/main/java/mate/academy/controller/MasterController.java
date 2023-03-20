package mate.academy.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.dto.mapper.MasterMapper;
import mate.academy.dto.mapper.OrderMapper;
import mate.academy.dto.request.MasterRequestDto;
import mate.academy.dto.response.MasterResponseDto;
import mate.academy.dto.response.OrderResponseDto;
import mate.academy.model.Master;
import mate.academy.service.MasterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/masters")
public class MasterController {
    private final MasterMapper mapper;
    private final MasterService service;
    private final OrderMapper orderMapper;

    public MasterController(MasterMapper mapper, MasterService service, OrderMapper orderMapper) {
        this.mapper = mapper;
        this.service = service;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public MasterResponseDto create(@RequestBody MasterRequestDto dto) {
        Master master = mapper.toModel(dto);
        service.create(master);
        return mapper.toResponseDto(master);
    }

    @PutMapping("/{id}")
    public MasterResponseDto update(@PathVariable Long id,
                                    @RequestBody MasterRequestDto dto) {
        Master master = mapper.toModel(dto);
        master.setId(id);
        service.update(master);
        return mapper.toResponseDto(master);
    }

    @GetMapping("/orders/{id}")
    public List<OrderResponseDto> getMasterOrders(@PathVariable Long id) {
        return service.getMasterOrdersById(id).stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/salary/{id}")
    public BigDecimal getMasterSalary(@PathVariable Long id) {
        return service.getMasterSalaryById(id);
    }
}
