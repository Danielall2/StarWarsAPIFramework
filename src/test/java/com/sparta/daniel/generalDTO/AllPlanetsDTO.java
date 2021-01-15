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

    List<DTOPlanets> dtoPlanetsList = new ArrayList<>();
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
        AllPlanetsDTO allPlanetsDTOTemporary;

        if (dtoPlanetsList.size() < count) {
            for (JSONObject json : results) {
                dtoPlanetsList.add((DTOPlanets) Injector.injectDTOGeneric((String) json.get("url")));
            }

            if (next != null) {
                allPlanetsDTOTemporary = (AllPlanetsDTO) Injector.injectDTOGeneric(getNext());

                for (JSONObject json : allPlanetsDTOTemporary.getResults()) {
                    dtoPlanetsList.add((DTOPlanets) Injector.injectDTOGeneric((String) json.get("url")));
                }

                allPlanetsDTOTemporary = (AllPlanetsDTO) Injector.injectDTOGeneric(allPlanetsDTOTemporary.getNext());


//                while (allPeopleDTOTemporary.getNext() != null) {

                while (true) {


                    for (JSONObject json : allPlanetsDTOTemporary.getResults()) {
                        dtoPlanetsList.add((DTOPlanets) Injector.injectDTOGeneric((String) json.get("url")));
                    }

                    if (allPlanetsDTOTemporary.getNext() == null) {
                        break;
                    }

                    allPlanetsDTOTemporary = (AllPlanetsDTO) Injector.injectDTOGeneric(allPlanetsDTOTemporary.getNext());


                }

            }
        }

        return dtoPlanetsList;

    }
}
