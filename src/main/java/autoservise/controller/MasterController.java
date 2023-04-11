package autoservise.controller;

import autoservise.dto.mapper.MasterMapper;
import autoservise.dto.mapper.OrderMapper;
import autoservise.dto.request.MasterRequestDto;
import autoservise.dto.response.MasterResponseDto;
import autoservise.dto.response.OrderResponseDto;
import autoservise.model.Master;
import autoservise.service.MasterService;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
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
    @ApiOperation(value = "create master")
    public MasterResponseDto create(@RequestBody MasterRequestDto dto) {
        Master master = mapper.toModel(dto);
        service.create(master);
        return mapper.toResponseDto(master);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update master by id")
    public MasterResponseDto update(@PathVariable Long id,
                                    @RequestBody MasterRequestDto dto) {
        Master master = mapper.toModel(dto);
        master.setId(id);
        service.update(master);
        return mapper.toResponseDto(master);
    }

    @GetMapping("/orders/{id}")
    @ApiOperation(value = "get masters orders by id")
    public List<OrderResponseDto> getMasterOrders(@PathVariable Long id) {
        return service.getMasterOrdersById(id).stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/salary/{id}")
    @ApiOperation(value = "get masters salary by id")
    public BigDecimal getMasterSalary(@PathVariable Long id) {
        return service.getMasterSalaryById(id);
    }
}
