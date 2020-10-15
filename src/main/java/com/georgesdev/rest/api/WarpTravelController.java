package com.georgesdev.rest.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.georgesdev.rest.entity.Galaxy;
import com.georgesdev.rest.exceptions.ResourceNotFoundException;
import com.georgesdev.rest.service.GalaxyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/space-travelers")
@Tag(name = "Warp Travels")
public class WarpTravelController {

  static final Logger log = LoggerFactory.getLogger(WarpTravelController.class);

  @Autowired
  private GalaxyService galaxyService;

  /**
   * REST API that calculate the arrival time to a specific galaxy.
   * @return arrivalTime
   */
  @Operation(summary = "Calculate the arrival time to a specific galaxy.")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful Response",
              content = @Content(schema = @Schema(implementation = String.class))
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = @Content(schema = @Schema(implementation = String.class))
          )
      }
  )
  @GetMapping(path = "/calculate-arrival-time")
  public ResponseEntity<String> calculateArrivalTime(
      @RequestParam(value = "galaxyId") Integer galaxyId,
      @RequestParam(value = "warpFactor") Double warpFactor) throws ResourceNotFoundException {

    log.info("** calculateArrivalTime **");

    var retrievedGalaxy = validateGalaxyExist(galaxyId);

    return new ResponseEntity<>("Estimated arrival time is: "
        + galaxyService.calculateArrivalTime(retrievedGalaxy, warpFactor), HttpStatus.OK);
  }

  /**
   * REST API that returns a list of all the galaxies available.
   * @return List<Galaxy>
   */
  @Operation(summary = "Get a list of galaxies.")
  @ApiResponses(
      @ApiResponse(
          responseCode = "200",
          description = "Successful Response",
          content = @Content(array = @ArraySchema(schema = @Schema(implementation = Galaxy.class)))
      )
  )
  @GetMapping(
      path = "/galaxies",
      produces = APPLICATION_JSON_VALUE
  )
  public ResponseEntity<List<Galaxy>> getGalaxies() {

    log.info("** getGalaxies **");
    return new ResponseEntity(galaxyService.findAllGalaxies(), HttpStatus.OK);
  }

  /**
   * REST API that "register" a new galaxy.
   * @return Galaxy
   */
  @Operation(summary = "Register a new galaxy.")
  @ApiResponses(
      @ApiResponse(
          responseCode = "200",
          description = "Successful Response",
          content = @Content(schema = @Schema(implementation = Galaxy.class))
      )
  )
  @PostMapping(
      path = "/newGalaxy",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE
  )
  public ResponseEntity<Galaxy> newGalaxy(
      @Validated @RequestBody Galaxy galaxy) {

    log.info("** newGalaxy **");
    return new ResponseEntity<>(galaxyService.saveGalaxy(galaxy), HttpStatus.OK);
  }

  /**
   * REST API that "update" a galaxy.
   * @return Galaxy
   */
  @Operation(summary = "Update a galaxy.")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful Response",
              content = @Content(schema = @Schema(implementation = Galaxy.class))
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = @Content(schema = @Schema(implementation = String.class))
          )
      }
  )
  @PutMapping(
      path = "/updateGalaxy/{galaxyId}",
      produces = APPLICATION_JSON_VALUE
  )
  public ResponseEntity<Galaxy> updateGalaxy(
      @PathVariable(value = "galaxyId") Integer id,
      @Validated @RequestBody Galaxy galaxyDetails ) throws ResourceNotFoundException {

    log.info("** updateGalaxy **");
    var retrievedGalaxy = validateGalaxyExist(id);

    retrievedGalaxy.setGalaxyName(galaxyDetails.getGalaxyName());
    retrievedGalaxy.setLightYears(galaxyDetails.getLightYears());
    return new ResponseEntity<>(galaxyService.saveGalaxy(retrievedGalaxy), HttpStatus.OK);
  }

  /**
   * REST API that "delete" a galaxy.
   * @return Galaxy
   */
  @Operation(summary = "Delete a galaxy.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful Response",
            content = @Content(schema = @Schema(implementation = String.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(implementation = String.class))
        )
      }
  )
  @DeleteMapping(path = "/deleteGalaxy/{galaxyId}")
  public ResponseEntity<String> deleteGalaxy(
      @PathVariable(value = "galaxyId") Integer id) throws ResourceNotFoundException {

    log.info("** deleteGalaxy **");

    var retrievedGalaxy = validateGalaxyExist(id);
    galaxyService.deleteGalaxy(retrievedGalaxy.getId());
    return new ResponseEntity<>("Galaxy deleted correctly", HttpStatus.OK);
  }

  /* Helper Method to avoid code duplication */
  private Galaxy validateGalaxyExist(Integer id) throws ResourceNotFoundException {
    return galaxyService.findGalaxyById(id).orElseThrow(() ->
        new ResourceNotFoundException("Galaxy not found for id # " + id));
  }

}
