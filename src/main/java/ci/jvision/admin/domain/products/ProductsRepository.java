package ci.jvision.admin.domain.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long>  {
    //CRUD

    @Query("SELECT p FROM Products p ORDER BY p.p_id DESC")
    List<Products> findAllDesc();
}
