package ci.jvision.admin.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductsUpdateRequestDto {
    private String p_name;
    private int p_price;
    private String p_desc;

    @Builder
    public ProductsUpdateRequestDto(String p_name, int p_price, String p_desc){
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_desc = p_desc;
    }
}
