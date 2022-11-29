package ci.jvision.admin.web.dto;

import ci.jvision.admin.config.auth.dto.SessionUser;
import ci.jvision.admin.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ProductsService productsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products", productsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null)
            model.addAttribute("userName",user.getName());
        return "index";
    }

    @GetMapping("/products/save")
    public String productsSave(){
        return "products-save";
    }

    @GetMapping("/products/update/{p_id}")
    public String productsUpdate(@PathVariable Long p_id, Model model){
        ProductsListResponseDto dto = productsService.findById(p_id);
        model.addAttribute("product", dto);

        return "products-update";
    }
}
