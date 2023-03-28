package mate.academy.controller;

import io.swagger.annotations.ApiOperation;
import mate.academy.dto.mapper.CarMapper;
import mate.academy.dto.request.CarRequestDto;
import mate.academy.dto.response.CarResponseDto;
import mate.academy.model.Car;
import mate.academy.service.CarService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarMapper mapper;
    private final CarService service;

    public CarController(CarMapper mapper, CarService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "create new car")
    public CarResponseDto create(@RequestBody CarRequestDto dto) {
        Car car = mapper.toModel(dto);
        service.create(car);
        return mapper.toResponseDto(car);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update car by id")
    public CarResponseDto update(@PathVariable Long id,
                                 @RequestBody CarRequestDto dto) {
        Car car = mapper.toModel(dto);
        car.setId(id);
        service.update(car);
        return mapper.toResponseDto(car);
    }
}
