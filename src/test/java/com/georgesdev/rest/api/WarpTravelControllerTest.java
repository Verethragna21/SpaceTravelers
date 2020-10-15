package com.georgesdev.rest.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.georgesdev.rest.entity.Galaxy;
import com.georgesdev.rest.exceptions.ResourceNotFoundException;
import com.georgesdev.rest.service.GalaxyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WarpTravelControllerTest {

  @InjectMocks
  private WarpTravelController controller;

  @Mock
  private GalaxyService galaxyService;

  @Test
  public void calculateArrivalTime() throws ResourceNotFoundException {
    when(galaxyService.findGalaxyById(any())).thenReturn(java.util.Optional.of(new Galaxy()));

    var responseEntity = controller.calculateArrivalTime(1, 2.0);

    assertNotNull(responseEntity.getBody());
  }

  @Test
  public void getGalaxies() {
    var responseEntity = controller.getGalaxies();
    assertNotNull(responseEntity.getBody());
  }

  @Test
  public void newGalaxy() {
    Galaxy galaxy = new Galaxy();

    when(galaxyService.saveGalaxy(any())).thenReturn(new Galaxy());

    var responseEntity = controller.newGalaxy(galaxy);
    assertNotNull(responseEntity.getBody());
  }

  @Test
  public void updateGalaxy() throws ResourceNotFoundException {

    when(galaxyService.findGalaxyById(any())).thenReturn(java.util.Optional.of(new Galaxy()));
    when(galaxyService.saveGalaxy(any())).thenReturn(new Galaxy());

    var responseEntity = controller.updateGalaxy(2, new Galaxy());

    verify(galaxyService, atLeastOnce()).findGalaxyById(anyInt());

    assertNotNull(responseEntity.getBody());
  }

  @Test
  public void deleteGalaxy() throws ResourceNotFoundException {
    when(galaxyService.findGalaxyById(any())).thenReturn(java.util.Optional.of(new Galaxy()));

    var responseEntity = controller.deleteGalaxy(2);

    verify(galaxyService, atLeastOnce()).findGalaxyById(anyInt());

    assertNotNull(responseEntity.getBody());
  }

}