package com.kreative.delb.application.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractApiRessource<T> {

  protected List<HashMap<String, Object>> filter(List<T> objetList, List<String> filterList) {
    List<HashMap<String, Object>> hashMapList = new ArrayList<>();
    objetList.stream()
        .forEach(
            objet -> {
              hashMapList.add(filter(objet, filterList));
            });
    return hashMapList;
  }

  protected HashMap<String, Object> filter(T objet, List<String> filterList) {
    HashMap<String, Object> map = new ObjectMapper().convertValue(objet, HashMap.class);
    if (filterList.isEmpty()) {
      return map;
    } else {
      HashMap<String, Object> objectReturned = new HashMap<>();
      for (String key : map.keySet()) {
        if (filterList.contains(key)) {
          objectReturned.put(key, map.get(key));
        }
      }
      return objectReturned;
    }
  }
}
