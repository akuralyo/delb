package com.kreative.delb.resource.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.resource.ViewsAuthor;

public class BookDto {

    @JsonView(ViewsAuthor.ApiPublic.class)
	private String id;

    @JsonView(ViewsAuthor.ApiPublic.class)
	private String name;

    private AuthorDto authorDto = new AuthorDto();

    public String getName() {
        return name;
    }

    public BookDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    public BookDto setId(String id) {
        this.id = id;
        return this;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public BookDto setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
        return this;
    }
}
