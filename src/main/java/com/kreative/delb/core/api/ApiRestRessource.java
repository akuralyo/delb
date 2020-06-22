package com.kreative.delb.core.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.kreative.delb.core.api.ApiPathVariable.PV_ID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface ApiRest<T> {

	/**
	 * API qui permet de sauvegarder un objet en BDD
	 *
	 * @param objectDto : Objet à sauvegarder
	 * @return
	 */
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<T> create(@RequestBody T objectDto);

	/**
	 * API qui permet de supprimer un objet en BDD
	 *
	 * @param id : identifiant en BDD qui identifie l'objet à supprimer
	 * @return
	 */
	@DeleteMapping(value = PV_ID, produces = APPLICATION_JSON_VALUE)
	ResponseEntity delete(@PathVariable String id);

	/**
	 * API qui permet de récupérer tous les objets en BDD
	 *
	 * @return
	 */
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	ResponseEntity<List<T>> findAll();

	/**
	 * API qui permet de récupérer tous les objets en BDD
	 *
	 * @return
	 */
	@GetMapping(produces = APPLICATION_JSON_VALUE, params = {"filterList"})
	ResponseEntity<List<HashMap<String, Object>>> findAllAndFilterAplly(@RequestParam List<String> filterList);

	/**
	 * API qui permet de récupérer un objet grâce à son ID
	 *
	 * @param id : Identifiant de l'objet en BDD
	 * @return
	 */
	@GetMapping(value = PV_ID, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<T> findOneById(@PathVariable String id);

	/**
	 * API qui permet de récupérer un objet grâce à son ID
	 *
	 * @param id : Identifiant de l'objet en BDD
	 * @return
	 */
	@GetMapping(value = PV_ID, produces = APPLICATION_JSON_VALUE, params = {"filterList"})
	ResponseEntity<HashMap<String, Object>> findOneByIdAndFilterApply(@PathVariable String id, @RequestParam List<String> filterList);

	/**
	 * API qui permet de mettre à jour un objet en BDD
	 *
	 * @param id        : Identifiant de l'obje en BDD
	 * @param objectDto : Objet qui contient les nouvelles données
	 * @return
	 */
	@PutMapping(value = PV_ID, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity update(@PathVariable String id, @RequestBody T objectDto);
}
