package com.georgesdev.rest.service;

import com.georgesdev.rest.dao.IGalaxyRepository;
import com.georgesdev.rest.entity.Galaxy;
import com.georgesdev.rest.util.Converters;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalaxyService {
  static final Logger log = LoggerFactory.getLogger(GalaxyService.class);

  @Autowired
  private IGalaxyRepository galaxyRepository;

  public List<Galaxy> findAllGalaxies() {
    return (List<Galaxy>) galaxyRepository.findAll();
  }

  public Optional<Galaxy> findGalaxyById(Integer id) {
    return galaxyRepository.findById(id);
  }

  public Galaxy saveGalaxy(Galaxy galaxy) {
    return galaxyRepository.save(galaxy);
  }

  public void deleteGalaxy(Integer id) {
    galaxyRepository.deleteById(id);
  }

  public String calculateArrivalTime(Galaxy retrievedGalaxy, Double warpFactor) {
    var normalizedFactor = warpFactor.intValue();
    var galaxyDistance = retrievedGalaxy.getLightYears();
    var speed = Math.pow(normalizedFactor, (10/3));

    log.info("** Travel Speed: " + speed + " **");

    var time = galaxyDistance / speed;
    time = Converters.convertTime(time);
    time = Converters.roundResult(time);

    log.info("** Normalized ETA: " + time + " **");

    return (int) time + " Days";
  }

}
