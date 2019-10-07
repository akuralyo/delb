package com.kreative.delb.resource.constants;

import org.springframework.stereotype.Component;

@Component
public class Api {

	public static final String PREFIXE = "/api";

	public static final String PUBLIC = "/public";

	public static final String PRIVATE = "/private";

	public static class PathVariable {
		public static final String PV_ID = "/{id}";

		public static final String PV_SELF = "/self";
	}

	public static class Resource {
		public static final String BOOKS = "/books";

		public static final String AUTHORS = "/authors";

		public static final String USERS = "/users";

		public static final String SEARCH = "/search";

		public static final String REINIT = "/init";
	}
}
