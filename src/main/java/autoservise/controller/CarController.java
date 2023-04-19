package autoservise.controller;

import autoservise.dto.mapper.CarMapper;
import autoservise.dto.request.CarRequestDto;
import autoservise.dto.request.create.CarRequestDtoForCreate;
import autoservise.dto.response.CarResponseDto;
import autoservise.model.Car;
import autoservise.service.CarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public CarResponseDto create(@RequestBody CarRequestDtoForCreate dto) {
        Car car = mapper.toModelForCreate(dto);
        service.create(car);
        return mapper.toResponseDto(car);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "update car by id")
    public CarResponseDto update(@PathVariable Long id,
                                 @RequestBody CarRequestDto dto) {
        Car car = mapper.toModel(dto);
        car.setId(id);
        service.update(car);
        return mapper.toResponseDto(car);
    }
}
