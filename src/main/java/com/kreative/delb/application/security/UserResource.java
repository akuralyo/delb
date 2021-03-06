package com.kreative.delb.application.security;

import com.kreative.delb.application.common.ApiRestPathVariable;
import com.kreative.delb.domain.service.user.model.User;
import com.kreative.delb.domain.service.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.kreative.delb.application.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.application.common.resource.constants.Api.PRIVATE;
import static com.kreative.delb.application.common.resource.constants.Api.Resource.USERS;

@RestController
@RequestMapping(PREFIXE + PRIVATE + USERS)
public class UserResource {

  private static final Logger LOGGER = Logger.getLogger(UserResource.class);

  @Autowired private UserService userService;

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @GetMapping(ApiRestPathVariable.PV_SELF)
  public ResponseEntity getSelf(Principal principal) {
    return new ResponseEntity(principal.getName(), HttpStatus.OK);
  }

  @PutMapping(ApiRestPathVariable.PV_SELF)
  public ResponseEntity update(Principal principal) {
    return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
  }
}
