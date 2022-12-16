package com.iqbalfa.electronic.repository;

import com.iqbalfa.electronic.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from m_product p", nativeQuery = true)
    List<Product> findAll();

    @Query(value = "select * from m_product p where p.productId = ?1", nativeQuery = true)
    Optional<Product> findByProductId(Long productId);

    @Transactional
    @Modifying
    @Query(value = "delete from m_product c where c.productId = ?1", nativeQuery = true)
    void deleteProductById(Long productId);
}