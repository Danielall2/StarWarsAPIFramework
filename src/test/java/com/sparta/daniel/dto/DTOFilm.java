package com.sparta.daniel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.daniel.injector.Injector;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "episode_id",
        "opening_crawl",
        "director",
        "producer",
        "release_date",
        "characters",
        "planets",
        "starships",
        "vehicles",
        "species",
        "created",
        "edited",
        "url",
})

public class DTOFilm extends ParentDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("episode_id")
    private int episode_id;

    @JsonProperty("opening_crawl")
    private String opening_crawl;

    @JsonProperty("director")
    private String director;

    @JsonProperty("producer")
    private String producer;

    // LocalDate
    @JsonProperty("release_date")
    private String release_date;

    // Array of Links to Species
    @JsonProperty("species")
    private List<String> species;

    // Array of Links to Starships
    @JsonProperty("starships")
    private List<String> starships;

    // Array of Links to Vehicles
    @JsonProperty("vehicles")
    private List<String> vehicles;

    // Array of Links to People
    @JsonProperty("characters")
    private List<String> characters;

    // Array of Links to Planets
    @JsonProperty("planets")
    private List<String> planets;

    // URL
    @JsonProperty("url")
    private String url;

    // Date
    @JsonProperty("created")
    private String created;

    // Date
    @JsonProperty("edited")
    private String edited;


    // No Argument Constructor

    public DTOFilm() {

    }


    // Simple getters for Each variable above with no alterations

    public String getTitle() {
        return title;
    }

    public int getEpisode_id() {
        return episode_id;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getRelease_date() {
        return release_date;
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

    public List<String> getCharacters() {
        return characters;
    }

    public List<String> getPlanets() {
        return planets;
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

    public List<DTOSpecies> getSpeciesListAsDTOs() {
        List<DTOSpecies> dtoSpeciesList = new ArrayList<>();
        DTOSpecies dtoSpeciesTemporary;

        for (String specimen : species) {
            dtoSpeciesTemporary = (DTOSpecies) Injector.injectDTOGeneric(specimen);
            dtoSpeciesList.add(dtoSpeciesTemporary);
        }

        return dtoSpeciesList;
    }

    public List<DTOStarships> getStarshipListAsDTOs() {
        List<DTOStarships> dtoStarshipsList = new ArrayList<>();
        DTOStarships dtoStarshipsTemporary;

        for (String starship : starships) {
            dtoStarshipsTemporary = (DTOStarships) Injector.injectDTOGeneric(starship);
            dtoStarshipsList.add(dtoStarshipsTemporary);
        }

        return dtoStarshipsList;
    }

    public List<DTOVehicles> getVehiclesListAsDTOs() {
        List<DTOVehicles> dtoVehiclesList = new ArrayList<>();
        DTOVehicles dtoVehiclesTemporary;

        for (String vehicle : vehicles) {
            dtoVehiclesTemporary = (DTOVehicles) Injector.injectDTOGeneric(vehicle);
            dtoVehiclesList.add(dtoVehiclesTemporary);
        }

        return dtoVehiclesList;
    }

    public List<DTOPeople> getCharacterListAsDTOs() {
        List<DTOPeople> dtoCharacterList = new ArrayList<>();
        DTOPeople dtoCharacterTemporary;

        for (String character : characters) {
            dtoCharacterTemporary = (DTOPeople) Injector.injectDTOGeneric(character);
            dtoCharacterList.add(dtoCharacterTemporary);
        }

        return dtoCharacterList;
    }

    public List<DTOPlanets> getPlanetsListAsDTOs() {
        List<DTOPlanets> dtoPlanetsList = new ArrayList<>();
        DTOPlanets dtoPlanetsTemporary;

        for (String planet : planets) {
            dtoPlanetsTemporary = (DTOPlanets) Injector.injectDTOGeneric(planet);
            dtoPlanetsList.add(dtoPlanetsTemporary);
        }

        return dtoPlanetsList;
    }
}
