package ci.jvision.admin.web;

import ci.jvision.admin.service.ProductsService;
import ci.jvision.admin.web.dto.ProductsListResponseDto;
import ci.jvision.admin.web.dto.ProductsSaveRequestDto;
import ci.jvision.admin.web.dto.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductsApiController {
    private final ProductsService productsService;

    @PostMapping("/api/v1/products") //localhost:8080/api/v1/products
    public Long save(@RequestBody ProductsSaveRequestDto requestDto) {
        return productsService.save(requestDto);
    }

    @PutMapping("/api/v1/products/{p_id}") //localhost:8080/api/v1/products
    public Long update(@PathVariable Long p_id, @RequestBody ProductsUpdateRequestDto requestDto) {
        return productsService.update(p_id, requestDto);
    }

    @DeleteMapping("/api/v1/products/{p_id}") //localhost:8080/api/v1/products
    public Long delete(@PathVariable Long p_id) {
        productsService.delete(p_id);
        return p_id;
    }

    @GetMapping("/api/v1/products/{p_id}")
    public ProductsListResponseDto findById (@PathVariable Long p_id){
        return productsService.findById(p_id);
    }
}
