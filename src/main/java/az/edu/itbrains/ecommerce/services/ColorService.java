package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.color.ColorCreateDto;
import az.edu.itbrains.ecommerce.dtos.color.ColorDto;
import az.edu.itbrains.ecommerce.dtos.color.ColorUpdateDto;
import az.edu.itbrains.ecommerce.models.Color;
import az.edu.itbrains.ecommerce.models.Size;
import az.edu.itbrains.ecommerce.payloads.results.Result;

import java.util.List;

public interface ColorService {
    List<ColorDto> getAllColors();

    Result saveColor(ColorCreateDto colorCreateDto);

    ColorUpdateDto findUpdatedColor(Long id);

    Result updateColor(Long id, ColorUpdateDto colorUpdateDto);

    Color getColorById(Long colorId);

}
