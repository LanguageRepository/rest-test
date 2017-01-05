package com.resttest.repository;

import com.resttest.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kvasa on 01.01.2017.
 */
public interface DepartmentJpaRepository extends JpaRepository<Department, Long>{

    Department getDepartmentByName(String name);

}
