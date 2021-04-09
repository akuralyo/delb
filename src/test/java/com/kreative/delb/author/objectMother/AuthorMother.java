package com.kreative.delb.author.objectMother;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.kreative.delb.application.author.dto.AuthorDto;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;

public class AuthorMother {

  public AuthorModel createAuthor(final int i) {
    final AuthorModel authorModel = new AuthorModel();

    authorModel.setIdAuthor(UUID.randomUUID().toString());
    authorModel.setFirstName("FirstName" + i);
    authorModel.setLastName("LastName" + i);
    authorModel.setNickName("NickName" + i);
    authorModel.setBirthday(LocalDate.now());
    authorModel.setAdresse("Adresse" + i);

    return authorModel;
  }

  public AuthorModel createAuthor(final int i, final String userId) {
    final AuthorModel authorModel = new AuthorModel();

    authorModel.setIdAuthor(userId);
    authorModel.setFirstName("FirstName" + i);
    authorModel.setLastName("LastName" + i);
    authorModel.setNickName("NickName" + i);
    authorModel.setBirthday(LocalDate.now());
    authorModel.setAdresse("Adresse" + i);
    authorModel.setAdresse("Adresse" + i);

    return authorModel;
  }

  public AuthorDto createAuthorDto(final AuthorModel author) {
    final AuthorDto authorDto = new AuthorDto();

    authorDto.setIdAuthor(author.getIdAuthor());
    authorDto.setFirstName(author.getFirstName());
    authorDto.setLastName(author.getLastName());
    authorDto.setNickName(author.getNickName());
    authorDto.setBirthday(author.getBirthday());
    authorDto.setAdresse(author.getAdresse());

    return authorDto;
  }

  public AuthorDto createAuthorDto(final int i) {
    final AuthorDto authorDto = new AuthorDto();

    authorDto.setFirstName("FirstName" + i);
    authorDto.setLastName("LastName" + i);
    authorDto.setNickName("NickName" + i);
    authorDto.setAdresse("Mon adresse" + i);

    return authorDto;
  }

  public List<AuthorModel> createAuthorList() {
    final List<AuthorModel> authorList = new ArrayList<>();
    authorList.add(createAuthor(0));
    authorList.add(createAuthor(1));
    authorList.add(createAuthor(2));
    return authorList;
  }

  public Map<String, String> createMapAuthorDto(final int i) {
    final Map<String, String> map = new HashMap<>();
    //
    map.put("firstName", "FirstName" + i);
    map.put("lastName", "LastName" + i);
    map.put("nickName", "NickName" + i);
    //
    return map;
  }
}
