package com.sparta.daniel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.daniel.injector.Injector;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "classification",
        "designation",
        "average_height",
        "skin_colors",
        "hair_colors",
        "eye_colors",
        "average_lifespan",
        "homeworld",
        "language",
        "people",
        "films",
        "created",
        "edited",
        "url"
})

public class DTOSpecies extends ParentDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("classification")
    private String classification;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("average_height")
    private String average_height;

    @JsonProperty("skin_colors")
    private String skin_colors;

    @JsonProperty("average_lifespan")
    private String average_lifespan;

    @JsonProperty("eye_colors")
    private String eye_colors;

    @JsonProperty("hair_colors")
    private String hair_colors;

    @JsonProperty("language")
    private String language;

    // Link
    @JsonProperty("homeworld")
    private String homeworld;

    // Array of Links to People
    @JsonProperty("people")
    private List<String> people;

    // Array of Links to Film
    @JsonProperty("films")
    private List<String> films;

    // Link
    @JsonProperty("url")
    private String url;

    // Date
    @JsonProperty("created")
    private String created;

    // Date
    @JsonProperty("edited")
    private String edited;


    // No Argument Constructor

    public DTOSpecies() {

    }


    // Simple getters for Each variable above with no alterations

    public String getName() {
        return name;
    }

    public String getClassification() {
        return classification;
    }

    public String getDesignation() {
        return designation;
    }

    public String getAverage_height() {
        return average_height;
    }

    public String getSkin_colors() {
        return skin_colors;
    }

    public String getAverage_lifespan() {
        return average_lifespan;
    }

    public String getEye_colors() {
        return eye_colors;
    }

    public String getHair_colors() {
        return hair_colors;
    }

    public String getLanguage() {
        return language;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public List<String> getPeople() {
        return people;
    }

    public List<String> getFilms() {
        return films;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }


    // Below are methods to help with Testing

    public DTOPlanets getHomeworldAsDTO() {
        return (DTOPlanets) Injector.injectDTOGeneric(homeworld);
    }

    public List<DTOPeople> getPeopleAsListDTOs() {
        List<DTOPeople> dtoPeopleList = new ArrayList<>();
        DTOPeople dtoPeopleTemporary;

        for (String person : people) {
            dtoPeopleTemporary = (DTOPeople) Injector.injectDTOGeneric(person);
            dtoPeopleList.add(dtoPeopleTemporary);
        }

        return dtoPeopleList;

    }

    public List<DTOFilm> getFilmsAsListDTOs() {
        List<DTOFilm> dtoFilmList = new ArrayList<>();
        DTOFilm dtoFilmTemporary;

        for (String film : films) {
            dtoFilmTemporary = (DTOFilm) Injector.injectDTOGeneric(film);
            dtoFilmList.add(dtoFilmTemporary);
        }

        return dtoFilmList;
    }


}
