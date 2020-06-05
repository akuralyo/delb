package com.kreative.delb.common.resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractRessourceApi<T> {

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
