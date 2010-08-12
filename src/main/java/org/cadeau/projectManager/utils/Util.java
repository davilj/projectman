package org.cadeau.projectManager.utils;

import java.util.Map;

public class Util {
  public static String makeString(Map<String,Object> mapKey2Value, String separator) {
    StringBuffer newString = new StringBuffer();
    int n=0;
    for (String key : mapKey2Value.keySet()) {
      Object value = mapKey2Value.get(key);
      String valueAsStr = value==null?"null":value.toString();
      newString.append(key + "-[" +  valueAsStr+ "]");
      if ((n++) < mapKey2Value.size()) {
        newString.append(separator);
      }
    }
    return newString.toString();
  }
}
