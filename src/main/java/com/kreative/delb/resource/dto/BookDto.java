package com.kreative.delb.resource.dto;

public class BookDto {
    private String id;

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
