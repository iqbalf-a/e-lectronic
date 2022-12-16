package com.iqbalfa.electronic.repository;

import com.iqbalfa.electronic.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from m_category c order by c.categoryId", nativeQuery = true)
    List<Category> findAll();

    @Transactional
    @Modifying
    @Query(value = "delete from m_category c where c.categoryId = ?1", nativeQuery = true)
    void deleteCategoryById(Long categoryId);

    @Query(value = "select * from m_category c where c.categoryId = ?1", nativeQuery = true)
    Optional<Category> findByCategoryById(Long categoryId);





}
