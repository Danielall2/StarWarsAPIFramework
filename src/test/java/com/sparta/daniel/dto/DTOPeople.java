package com.sparta.daniel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.daniel.connector.ConnectionManager;
import com.sparta.daniel.injector.Injector;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "height",
        "mass",
        "hair_color",
        "skin_color",
        "eye_color",
        "birth_year",
        "gender",
        "homeworld",
        "films",
        "species",
        "vehicles",
        "starships",
        "created",
        "edited",
        "url",
})

public class DTOPeople extends ParentDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private String birth_year;

    @JsonProperty("eye_color")
    private String eye_color;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("hair_color")
    private String hair_color;

    @JsonProperty("height")
    private int height;

    @JsonProperty("mass")
    private int mass;

    @JsonProperty("skin_color")
    private String skin_color;

    // Link to Planets
//    @JsonProperty("homeworld")
//    private DTOPlanets homeworld;
//
//    // Array of Links to Film
//    @JsonProperty("films")
//    private List<DTOFilm> films;
//
//    // Array of Links to Species
//    @JsonProperty("species")
//    private List<DTOSpecies> species;
//
//    // Array of Links to Starships
//    @JsonProperty("starships")
//    private List<DTOStarships> starships;
//
//    // Array of Links to Vehicles
//    @JsonProperty("vehicles")
//    private List<DTOVehicles> vehicles;


    // Link to Planets
    @JsonProperty("homeworld")
    private String homeworld;

    // Array of Links to Film
    @JsonProperty("films")
    private List<String> films;

    // Array of Links to Species
    @JsonProperty("species")
    private List<String> species;

    // Array of Links to Starships
    @JsonProperty("starships")
    private List<String> starships;

    // Array of Links to Vehicles
    @JsonProperty("vehicles")
    private List<String> vehicles;


    // Link
    @JsonProperty("url")
    private String url;

    // Date and Time in form of String
    @JsonProperty("created")
    private String created;

    // Date and Time in form of String
    @JsonProperty("edited")
    private String edited;


    // No Argument Constructor

    public DTOPeople() {

    }


    // Simple getters for Each variable above with no alterations

    public String getName() {
        return name;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public String getEye_color() {
        return eye_color;
    }

    public String getGender() {
        return gender;
    }

    public String getHair_color() {
        return hair_color;
    }

    public int getHeight() {
        return height;
    }

    public int getMass() {
        return mass;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public List<String> getFilms() {
        return films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public List<String> getStarships() {
        return starships;
    }

    public List<String> getVehicles() {
        return vehicles;
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


    // Below are created methods to help with testing

    public DTOPlanets getHomeWorldAsDTOPlanet() {
        return (DTOPlanets) Injector.injectDTOGeneric(homeworld);
    }


    public List<DTOFilm> getFilmsAsDTOs() {
        List<DTOFilm> dtoFilmList = new ArrayList<>();
        DTOFilm dtoFilmTemporary;

        for(String film : films) {
            dtoFilmTemporary = (DTOFilm) Injector.injectDTOGeneric(film);
            dtoFilmList.add(dtoFilmTemporary);
        }

        return dtoFilmList;

    }

    public List<DTOSpecies> getSpeciesAsListDTOs() {
        List<DTOSpecies> dtoSpeciesList = new ArrayList<>();
        DTOSpecies dtoSpeciesTemporary;

        for(String specimen : species) {
            dtoSpeciesTemporary = (DTOSpecies) Injector.injectDTOGeneric(specimen);
            dtoSpeciesList.add(dtoSpeciesTemporary);
        }

        return dtoSpeciesList;

    }

    public List<DTOStarships> getStarshipsAsListDTOs() {
        List<DTOStarships> dtoStarshipsList = new ArrayList<>();
        DTOStarships dtoStarshipsTemporary;

        for(String starship : starships) {
            dtoStarshipsTemporary = (DTOStarships) Injector.injectDTOGeneric(starship);
            dtoStarshipsList.add(dtoStarshipsTemporary);
        }

        return dtoStarshipsList;
    }

    public List<DTOVehicles> getVehiclesAsListDTOs() {
        List<DTOVehicles> dtoVehiclesList = new ArrayList<>();
        DTOVehicles dtoVehiclesTemporary;

        for (String vehicle : vehicles) {
            dtoVehiclesTemporary = (DTOVehicles) Injector.injectDTOGeneric(vehicle);
            dtoVehiclesList.add(dtoVehiclesTemporary);
        }

        return dtoVehiclesList;
    }

    // Use below for date formatting

    public LocalDate getCreatedLocalDate() {
        //System.out.println(LocalDate.parse(created.substring(0, 10)));
        return LocalDate.parse(created.substring(0, 10));
    }

    public LocalDate getEditedLocalDate() {
        //System.out.println(LocalDate.parse(edited.substring(0, 10)));
        return LocalDate.parse(edited.substring(0, 10));
    }


}
