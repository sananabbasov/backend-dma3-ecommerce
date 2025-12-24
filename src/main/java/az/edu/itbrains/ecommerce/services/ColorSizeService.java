package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.colorSize.ColorSizeCreateDto;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.payloads.results.Result;

import java.util.List;

public interface ColorSizeService {
    Result createColorSize(List<ColorSizeCreateDto> colorSizes, Product product);
}
