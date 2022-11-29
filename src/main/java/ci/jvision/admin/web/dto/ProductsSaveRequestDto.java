package ci.jvision.admin.web.dto;

import ci.jvision.admin.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {
    private String p_name;
    private int p_price;
    private String p_desc;

    @Builder
    public ProductsSaveRequestDto(String p_name, int p_price, String p_desc){
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_desc = p_desc;
    }

    public Products toEntity(){
        return Products.builder()
                .p_name(p_name)
                .p_price(p_price)
                .p_desc(p_desc)
                .build();
    }
}
