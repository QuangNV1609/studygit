package com.quangnv.uet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quangnv.uet.entities.orm.ColorEntity;

public interface ColorRepository extends JpaRepository<ColorEntity, String>{

}
