package com.kreative.delb.resource.dto;

public class BookDto {
    private String name;
    private AuthorDto authorDto = new AuthorDto();

    public String getName() {
        return name;
    }

    public BookDto setName(String name) {
        this.name = name;
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
