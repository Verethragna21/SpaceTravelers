package com.georgesdev.rest.dao;

import com.georgesdev.rest.entity.Galaxy;
import org.springframework.data.repository.CrudRepository;

public interface IGalaxyRepository extends CrudRepository<Galaxy, Integer> { }
