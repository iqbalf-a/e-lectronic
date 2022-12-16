package com.iqbalfa.electronic.repository;

import com.iqbalfa.electronic.model.Category;
import com.iqbalfa.electronic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from m_user mu order by mu.user_id", nativeQuery = true)
    List<User> findAll();

    @Transactional
    @Modifying
    @Query(value = "delete from m_user mu where mu.user_id = ?1", nativeQuery = true)
    void deleteUserById(Long userId);

    @Query(value = "select * from m_user mu where mu.user_id = ?1", nativeQuery = true)
    Optional<User> findByUserById(Long userId);





}
