package com.georgesdev.rest.util;

public class Converters {

  /**
   * Method used to convert the ETA to days.
   * @param time time to arrive a galaxy
   * @return time standarized to "days".
   */
  public static double convertTime(double time){
    return time = time * 365.25;
  }

  /**
   * Method used to round the time
   * @param time
   * @return roundedTime
   */
  public static long roundResult(double time) {
    var roundedTime = Math.round(time);
    return roundedTime = roundedTime / 100;
  }

}
