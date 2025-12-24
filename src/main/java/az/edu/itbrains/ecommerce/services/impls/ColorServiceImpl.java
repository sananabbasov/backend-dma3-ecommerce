package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.color.ColorCreateDto;
import az.edu.itbrains.ecommerce.dtos.color.ColorDto;
import az.edu.itbrains.ecommerce.dtos.color.ColorUpdateDto;
import az.edu.itbrains.ecommerce.models.Color;
import az.edu.itbrains.ecommerce.models.Size;
import az.edu.itbrains.ecommerce.payloads.results.Result;
import az.edu.itbrains.ecommerce.payloads.results.error.ErrorResult;
import az.edu.itbrains.ecommerce.payloads.results.success.SuccessResult;
import az.edu.itbrains.ecommerce.repositories.ColorRepository;
import az.edu.itbrains.ecommerce.services.ColorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<ColorDto> getAllColors() {
        List<Color> colors = colorRepository.findAll();
        if (!colors.isEmpty()) {
            return colors.stream().map(color -> modelMapper.map(color, ColorDto.class)).toList();
        }
        return List.of();
    }

    @Override
    public Result saveColor(ColorCreateDto colorCreateDto) {
        try {
            Color color = new Color();
            color.setName(colorCreateDto.getName());
            colorRepository.save(color);
            return new SuccessResult("Color created successfully");
        }catch (Exception e){
            return new ErrorResult(e.getMessage());
        }
    }

    @Override
    public ColorUpdateDto findUpdatedColor(Long id) {
        Color color = colorRepository.findById(id).orElseThrow();
        ColorUpdateDto colorUpdateDto = modelMapper.map(color, ColorUpdateDto.class);
        return colorUpdateDto;
    }

    @Override
    public Result updateColor(Long id, ColorUpdateDto colorUpdateDto) {
        Color color = colorRepository.findById(id).orElseThrow();
        color.setName(colorUpdateDto.getName());
        colorRepository.save(color);
        return new SuccessResult("Color updated successfully");
    }

    @Override
    public Color getColorById(Long colorId) {
        return colorRepository.findById(colorId).orElseThrow();
    }


}
