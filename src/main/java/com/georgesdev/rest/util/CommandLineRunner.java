package com.georgesdev.rest.util;

import com.georgesdev.rest.dao.IGalaxyRepository;
import com.georgesdev.rest.entity.Galaxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
  
  @Autowired
  private IGalaxyRepository galaxyRepository;


  @Override
  public void run(String... args) throws Exception {
    galaxyRepository.deleteAll();

    //Here we are adding sample data to the H2 db.
    galaxyRepository.save(new Galaxy("Alfa Centauri", 4370000));
    galaxyRepository.save(new Galaxy("Galaxia M31", 2500000));
    galaxyRepository.save(new Galaxy("Galaxia M83", 1500000));

  }
}
