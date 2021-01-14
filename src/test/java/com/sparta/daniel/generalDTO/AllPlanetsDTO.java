package com.sparta.daniel.generalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.daniel.dto.DTOPlanets;
import com.sparta.daniel.dto.ParentDTO;
import com.sparta.daniel.injector.Injector;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "count",
        "next",
        "previous",
        "results",
})

public class AllPlanetsDTO extends ParentDTO {

    @JsonProperty("count")
    private int count;

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private String previous;

    @JsonProperty("results")
    private List<JSONObject> results;

    public AllPlanetsDTO() {
    }

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<JSONObject> getResults() {
        return results;
    }


    public List<DTOPlanets> getAllPlanetsAsListDTOs() {
        List<DTOPlanets> dtoPlanetsList = new ArrayList<>();

        for (JSONObject json : results) {
            dtoPlanetsList.add((DTOPlanets) Injector.injectDTOGeneric((String) json.get("url")));
        }

        return dtoPlanetsList;

    }
}
