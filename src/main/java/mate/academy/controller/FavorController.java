package mate.academy.controller;

import mate.academy.dto.mapper.FavorMapper;
import mate.academy.dto.request.FavorRequestDto;
import mate.academy.dto.response.FavorResponseDto;
import mate.academy.model.Favor;
import mate.academy.model.MasterSalaryStatus;
import mate.academy.service.FavorService;
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
    public FavorResponseDto create(@RequestBody FavorRequestDto dto) {
        Favor favor = mapper.toModel(dto);
        favor.setMasterStatus(MasterSalaryStatus.UNPAID);
        service.create(favor);
        return mapper.toResponseDto(favor);
    }

    @PutMapping("/{id}")
    public FavorResponseDto update(@PathVariable Long id,
                                   @RequestBody FavorRequestDto dto) {
        Favor favor = mapper.toModel(dto);
        favor.setId(id);
        service.update(favor);
        return mapper.toResponseDto(favor);
    }

    @PutMapping("/change-status/{id}")
    public FavorResponseDto changeStatus(@PathVariable Long id, String status) {
        Favor favor = service.getById(id);
        service.changeStatus(favor,status);
        return mapper.toResponseDto(favor);
    }
}
