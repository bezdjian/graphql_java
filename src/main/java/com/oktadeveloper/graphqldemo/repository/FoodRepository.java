package com.oktadeveloper.graphqldemo.repository;

import com.oktadeveloper.graphqldemo.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
