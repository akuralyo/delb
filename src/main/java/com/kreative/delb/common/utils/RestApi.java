package com.kreative.delb.common.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kreative.delb.common.resource.constants.Api.PathVariable.PV_ID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface RestApi<T> {

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<T> create(@RequestBody T object);

	@DeleteMapping(value = PV_ID, produces = APPLICATION_JSON_VALUE)
	ResponseEntity delete(@PathVariable String id);

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	ResponseEntity<List<T>> findAll();

	@GetMapping(value = PV_ID, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<T> findOneById(@PathVariable String id);

	@PutMapping(value = PV_ID, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity update(@PathVariable String id, @RequestBody T authorDto);
}
