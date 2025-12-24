package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.size.SizeCreateDto;
import az.edu.itbrains.ecommerce.dtos.size.SizeDto;
import az.edu.itbrains.ecommerce.dtos.size.SizeUpdateDto;
import az.edu.itbrains.ecommerce.models.Size;
import az.edu.itbrains.ecommerce.payloads.results.Result;
import az.edu.itbrains.ecommerce.payloads.results.error.ErrorResult;
import az.edu.itbrains.ecommerce.payloads.results.success.SuccessResult;
import az.edu.itbrains.ecommerce.repositories.SizeRepository;
import az.edu.itbrains.ecommerce.services.SizeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<SizeDto> getAllSizes() {
        List<Size> sizes = sizeRepository.findAll();
        if (!sizes.isEmpty()) {
            return sizes.stream().map(size -> modelMapper.map(size, SizeDto.class)).toList();
        }
        return List.of();
    }

    @Override
    public Result saveSize(SizeCreateDto sizeCreateDto) {
        try {
            Size size = new Size();
            size.setSize(sizeCreateDto.getSize());
            sizeRepository.save(size);
            return new SuccessResult("Size created successfully");
        }catch (Exception e){
            return new ErrorResult(e.getMessage());
        }
    }

    @Override
    public SizeUpdateDto findUpdatedSize(Long id) {
        Size size = sizeRepository.findById(id).orElseThrow();
        SizeUpdateDto sizeUpdateDto = modelMapper.map(size, SizeUpdateDto.class);
        return sizeUpdateDto;
    }

    @Override
    public Result updateSize(Long id, SizeUpdateDto sizeUpdateDto) {
        Size size = sizeRepository.findById(id).orElseThrow();
        size.setSize(sizeUpdateDto.getSize ());
        sizeRepository.save(size);
        return new SuccessResult("Size updated successfully");
    }

    @Override
    public Size getSizeById(Long sizeId) {
        return sizeRepository.findById(sizeId).orElseThrow();
    }
}
