package com.kreative.delb.application.common.resource.constants;

import org.springframework.stereotype.Component;

@Component
public class Api {

  public static final String PREFIXE = "/api";

  public static final String PRIVATE = "/private";

  public static final String PUBLIC = "/public";

  public static class Resource {
    public static final String AUTHORS = "/authors";

    public static final String BOOKS = "/books";

    public static final String SEARCH = "/search";

    public static final String USERS = "/users";
  }
}
