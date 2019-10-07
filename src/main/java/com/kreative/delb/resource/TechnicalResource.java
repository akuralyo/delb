package com.kreative.delb.resource;

import com.kreative.delb.service.technical.ResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kreative.delb.resource.constants.Api.PREFIXE;
import static com.kreative.delb.resource.constants.Api.PUBLIC;
import static com.kreative.delb.resource.constants.Api.Resource.REINIT;

@RestController
@RequestMapping(PREFIXE + PUBLIC + REINIT)
public class TechnicalResource {

	@Autowired
	private ResetService resetService;

	@GetMapping
	public ResponseEntity init() {
		resetService.init();
		return new ResponseEntity(HttpStatus.OK);
	}
}
