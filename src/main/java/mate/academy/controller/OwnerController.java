package mate.academy.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.dto.mapper.OrderMapper;
import mate.academy.dto.mapper.OwnerMapper;
import mate.academy.dto.request.OwnerRequestDto;
import mate.academy.dto.response.OrderResponseDto;
import mate.academy.dto.response.OwnerResponseDto;
import mate.academy.model.Owner;
import mate.academy.service.impl.OwnerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerMapper mapper;
    private final OwnerServiceImpl service;
    private final OrderMapper orderMapper;

    public OwnerController(OwnerMapper mapper, OwnerServiceImpl service, OrderMapper orderMapper) {
        this.mapper = mapper;
        this.service = service;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public OwnerResponseDto create(@RequestBody OwnerRequestDto dto) {
        Owner owner = mapper.toModel(dto);
        service.create(owner);
        return mapper.toResponseDto(owner);
    }

    @PutMapping("/{id}")
    public OwnerResponseDto update(@PathVariable Long id,
                                   @RequestBody OwnerRequestDto dto) {
        Owner owner = mapper.toModel(dto);
        owner.setId(id);
        service.update(owner);
        return mapper.toResponseDto(owner);
    }

    @GetMapping("/orders/{id}")
    public List<OrderResponseDto> getOrderListByUserId(@PathVariable Long id) {
        return service.getOrdersByOwnerId(id).stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
