package autoservise.controller;

import autoservise.dto.mapper.FavorMapper;
import autoservise.dto.request.FavorRequestDto;
import autoservise.dto.request.create.FavorRequestDtoForCreate;
import autoservise.dto.response.FavorResponseDto;
import autoservise.model.Favor;
import autoservise.service.FavorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favors")
public class FavorController {
    private final FavorMapper mapper;
    private final FavorService service;

    public FavorController(FavorMapper mapping, FavorService service) {
        this.mapper = mapping;
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "create favor")
    public FavorResponseDto create(@RequestBody FavorRequestDtoForCreate dto) {
        Favor favor = mapper.toModelForCreate(dto);
        service.create(favor);
        return mapper.toResponseDto(favor);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "update favor by id")
    public FavorResponseDto update(@PathVariable Long id,
                                   @RequestBody FavorRequestDto dto) {
        Favor favor = mapper.toModel(dto);
        favor.setId(id);
        service.update(favor);
        return mapper.toResponseDto(favor);
    }

    @PutMapping("/change-status/{id}")
    @ApiOperation(value = "change favor status bu id")
    public FavorResponseDto changeStatus(@PathVariable Long id,
                                          String status) {
        Favor favor = service.getById(id);
        service.changeStatus(favor,status);
        return mapper.toResponseDto(favor);
    }
}
