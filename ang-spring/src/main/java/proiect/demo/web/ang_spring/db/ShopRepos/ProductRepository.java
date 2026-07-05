package proiect.demo.web.ang_spring.db.ShopRepos;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.Shop.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
