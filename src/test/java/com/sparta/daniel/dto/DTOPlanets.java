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
        "rotation_period",
        "orbital_period",
        "diameter",
        "climate",
        "gravity",
        "terrain",
        "surface_water",
        "population",
        "residents",
        "films",
        "created",
        "edited",
        "url"
})

public class DTOPlanets extends ParentDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("diameter")
    private String diameter;

    @JsonProperty("rotation_period")
    private String rotation_period;

    @JsonProperty("orbital_period")
    private String orbital_period;

    @JsonProperty("gravity")
    private String gravity;

    @JsonProperty("population")
    private String population;

    @JsonProperty("climate")
    private String climate;

    @JsonProperty("terrain")
    private String terrain;

    @JsonProperty("surface_water")
    private String surface_water;

//    // Array of Links to People
//    @JsonProperty("residents")
//    private List<DTOPeople> residents;
//
//    // Array of Links to Film
//    @JsonProperty("films")
//    private List<DTOFilm> films;


    // Array of Links to People
    @JsonProperty("residents")
    private List<String> residents;

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

    public DTOPlanets() {

    }


    // Simple getters for Each variable above with no alterations

    public String getName() {
        return name;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getRotation_period() {
        return rotation_period;
    }

    public String getOrbital_period() {
        return orbital_period;
    }

    public String getGravity() {
        return gravity;
    }

    public String getPopulation() {
        return population;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getSurface_water() {
        return surface_water;
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

    public List<DTOPeople> getResidentsAsListDTOs() {
        List<DTOPeople> dtoPeopleList = new ArrayList<>();
        DTOPeople dtoPeopleTemporary;

        for (String resident : residents) {
            dtoPeopleTemporary = (DTOPeople) Injector.injectDTOGeneric(resident);
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
