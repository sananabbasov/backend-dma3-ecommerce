package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.product.*;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.payloads.results.Result;

import java.util.List;

public interface ProductService {

    List<ProductHomeDto> getAllHomeProducts();

    ProductDetailDto getProductDetail(Long id);

    Result createProduct(ProductCreateDto productCreateDto);

    List<ProductDashboardDto> getDashboardProducts();

    List<ProductFeaturedDto> getFeaturedProducts();

    List<ProductHotTrendDto> getHotTrendProducts();

    List<ProductBestSellerDto> getBestSellerProducts();

    Product getProductById(Long productId);
}
