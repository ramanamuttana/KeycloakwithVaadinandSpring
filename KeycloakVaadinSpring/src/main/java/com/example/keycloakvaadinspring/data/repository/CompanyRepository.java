package com.example.keycloakvaadinspring.data.repository;


import com.example.keycloakvaadinspring.data.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
