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
        "model",
        "manufacturer",
        "cost_in_credits",
        "length",
        "max_atmosphering_speed",
        "crew",
        "passengers",
        "cargo_capacity",
        "consumables",
        "vehicle_class",
        "pilots",
        "films",
        "created",
        "edited",
        "url"
})

public class DTOVehicles extends ParentDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("model")
    private String model;

    @JsonProperty("vehicle_class")
    private String vehicle_class;

    @JsonProperty("manufacturer")
    private String manufacturer;

    @JsonProperty("length")
    private String length;

    @JsonProperty("cost_in_credits")
    private String cost_in_credits;

    @JsonProperty("crew")
    private String crew;

    @JsonProperty("passengers")
    private String passengers;

    @JsonProperty("max_atmosphering_speed")
    private String max_atmosphering_speed;

    @JsonProperty("cargo_capacity")
    private String cargo_capacity;

    @JsonProperty("consumables")
    private String consumables;

    // Array of Links to Film
    @JsonProperty("films")
    private List<String> films;

    // Array of Links to People
    @JsonProperty("pilots")
    private List<String> pilots;

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

    public DTOVehicles() {

    }


    // Simple getters for Each variable above with no alterations

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getVehicle_class() {
        return vehicle_class;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getLength() {
        return length;
    }

    public String getCost_in_credits() {
        return cost_in_credits;
    }

    public String getCrew() {
        return crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public String getMax_atmosphering_speed() {
        return max_atmosphering_speed;
    }

    public String getCargo_capacity() {
        return cargo_capacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public List<String> getFilms() {
        return films;
    }

    public List<String> getPilots() {
        return pilots;
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

    public List<DTOFilm> getFilmsAsListDTOs() {
        List<DTOFilm> dtoFilmList = new ArrayList<>();
        DTOFilm dtoFilmTemporary;

        for (String film : films) {
            dtoFilmTemporary = (DTOFilm) Injector.injectDTOGeneric(film);
            dtoFilmList.add(dtoFilmTemporary);
        }

        return dtoFilmList;
    }

    public List<DTOPeople> getPilotsAsListDTOs() {
        List<DTOPeople> dtoPilotList = new ArrayList<>();
        DTOPeople dtoPilotTemporary;

        for (String pilot : pilots) {
            dtoPilotTemporary = (DTOPeople) Injector.injectDTOGeneric(pilot);
            dtoPilotList.add(dtoPilotTemporary);
        }

        return dtoPilotList;
    }
}
