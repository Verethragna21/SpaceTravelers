package com.georgesdev.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.georgesdev.rest.dao.IGalaxyRepository;
import com.georgesdev.rest.entity.Galaxy;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GalaxyServiceTest {

  @InjectMocks
  private GalaxyService service;

  @Mock
  private IGalaxyRepository repository;

  @Test
  public void findAllGalaxies() {
    when(repository.findAll()).thenReturn(new ArrayList<>());

    var galaxyList = service.findAllGalaxies();

    assertNotNull(galaxyList);
  }

  @Test
  public void findGalaxyById() {
    var galaxyList = service.findGalaxyById(1);

    assertNotNull(galaxyList);
  }

  @Test
  public void saveGalaxy() {
    Galaxy galaxyToSave = new Galaxy();

    when(repository.save(any())).thenReturn(new Galaxy());

    var galaxy = service.saveGalaxy(galaxyToSave);

    assertNotNull(galaxy);
  }

  @Test
  public void deleteGalaxy() {
    service.deleteGalaxy(1);
    verify(repository, atLeastOnce()).deleteById(anyInt());
  }

  @Test
  public void calculateArrivalTime() {
    var galaxy = new Galaxy();
    galaxy.setId(2);
    galaxy.setGalaxyName("Alfa Centaury");
    galaxy.setLightYears(4370000);
    var stringValue = service.calculateArrivalTime(galaxy, 2.0);

    assertEquals(stringValue, "1995178 Days");
  }



}