package ci.jvision.admin.service;

import ci.jvision.admin.domain.products.Products;
import ci.jvision.admin.domain.products.ProductsRepository;
import ci.jvision.admin.web.dto.ProductsListResponseDto;
import ci.jvision.admin.web.dto.ProductsSaveRequestDto;
import ci.jvision.admin.web.dto.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Transactional
    public Long save(ProductsSaveRequestDto requestDto) {
        return productsRepository.save(requestDto.toEntity()).getP_id();
    }

    @Transactional
    public Long update(Long p_id, ProductsUpdateRequestDto requestDto) {
        Products products = productsRepository.findById(p_id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+ p_id));
        products.update(requestDto.getP_name(), requestDto.getP_price(), requestDto.getP_desc());

        return p_id;
    }

    @Transactional
    public void delete(Long p_id) {
        Products products = productsRepository.findById(p_id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+ p_id));
        productsRepository.delete(products);
    }

    @Transactional
    public ProductsListResponseDto findById(Long p_id) {
        //id와 동일한 레코드를 DB로부터 검색해서 Posts 객체로 받아야 함
        Products entity = productsRepository.findById(p_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + p_id));

        return new ProductsListResponseDto(entity);
    }

    @Transactional
    public List<ProductsListResponseDto> findAllDesc() {
        return productsRepository.findAllDesc().stream()
                .map(ProductsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
