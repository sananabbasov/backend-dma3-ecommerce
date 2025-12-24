package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.size.SizeCreateDto;
import az.edu.itbrains.ecommerce.dtos.size.SizeDto;
import az.edu.itbrains.ecommerce.dtos.size.SizeUpdateDto;
import az.edu.itbrains.ecommerce.models.Size;
import az.edu.itbrains.ecommerce.payloads.results.Result;

import java.util.List;

public interface SizeService {
    List<SizeDto> getAllSizes();

    Result saveSize(SizeCreateDto sizeCreateDto);

    SizeUpdateDto findUpdatedSize(Long id);

    Result updateSize(Long id, SizeUpdateDto sizeUpdateDto);

    Size getSizeById(Long sizeId);
}
