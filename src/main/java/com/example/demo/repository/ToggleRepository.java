package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * toggle repository
 */
@Repository
public interface ToggleRepository {

    /**
     * init method for toggle local application cache map
     * @return redis toggle cache
     */
    Map<String, Boolean> getToggleMap();
}
