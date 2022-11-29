package ci.jvision.admin.domain.products;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Products {

    //테이블의 각 필드 정의
    //기본 키 정의(auto_increment 속성 부여-자동 증가)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long p_id;

    @Column(nullable = false)
    private String p_name;

    @Column(nullable = false)
    private int p_price;

    @Column(length=500, nullable = true)
    private String p_desc;

    @Builder
    public Products(Long p_id, String p_name, int p_price, String p_desc){
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_desc = p_desc;
    }

    public void update(String p_name, int p_price, String p_desc){
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_desc = p_desc;
    }

}
