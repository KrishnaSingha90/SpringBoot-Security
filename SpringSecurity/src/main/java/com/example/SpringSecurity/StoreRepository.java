package com.example.SpringSecurity;


import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<ProductStore, Long> {

}
