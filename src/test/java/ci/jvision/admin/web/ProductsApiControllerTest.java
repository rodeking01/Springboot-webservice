package ci.jvision.admin.web;

import ci.jvision.admin.domain.products.Products;
import ci.jvision.admin.domain.products.ProductsRepository;
import ci.jvision.admin.web.dto.ProductsSaveRequestDto;
import ci.jvision.admin.web.dto.ProductsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductsRepository productsRepository;

    @After
    public void cleanup(){
        productsRepository.deleteAll();
    }

    @Test
    public void Products_등록된다() throws Exception{

        //클라이언트의 요청 데이터
        String p_name = "p_name";
        int p_price = 8000;
        String p_desc = "p_desc";

        //등록 요청 데이터 객체
        ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                .p_name(p_name)
                .p_price(p_price)
                .p_desc(p_desc)
                .build();

        String url = "http://localhost:" + port + "/api/v1/products";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Products> all = productsRepository.findAll();
        assertThat(all.get(0).getP_name()).isEqualTo(p_name);
        assertThat(all.get(0).getP_price()).isEqualTo(p_price);
        assertThat(all.get(0).getP_desc()).isEqualTo(p_desc);
    }

    @Test
    public void Products_수정된다() throws Exception{
        Products savedProducts = productsRepository.save(Products.builder()
                        .p_name("p_name")
                        .p_price(80000)
                        .p_desc("p_desc")
                .build());

        //2. 수정하고자 하는 레코드의 ID, 제목, 내용 값 설정
        Long updateP_id = savedProducts.getP_id();
        String expectedP_name = "name2";
        int expectedP_price = 0;
        String expectedP_desc = "desc2";

        //3. 클라이언트가 수정 요청(url, Http-Put, UpdateRequestDto, Long.class)
        ProductsUpdateRequestDto requestDto = ProductsUpdateRequestDto.builder()
                .p_name(expectedP_name)
                .p_price(expectedP_price)
                .p_desc(expectedP_desc)
                .build();

        String url = "http://localhost:" + port + "/api/v1/products/" + updateP_id;

        HttpEntity<ProductsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //DB에 저장된 레코드 검색, 검색한 결과 비교(expectedTitle, expectedContent)
        List<Products> all = productsRepository.findAll();
        assertThat(all.get(0).getP_name()).isEqualTo(expectedP_name);
        assertThat(all.get(0).getP_price()).isEqualTo(expectedP_price);
        assertThat(all.get(0).getP_desc()).isEqualTo(expectedP_desc);
    }
}
