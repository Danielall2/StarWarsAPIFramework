package com.sparta.daniel.generalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.daniel.dto.DTOVehicles;
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

public class AllVehiclesDTO extends ParentDTO {

    List<DTOVehicles> dtoVehicleList = new ArrayList<>();
    @JsonProperty("count")
    private int count;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private String previous;
    @JsonProperty("results")
    private List<JSONObject> results;

    public AllVehiclesDTO() {
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

    public List<DTOVehicles> getAllVehiclesAsListDTOs() {
        AllVehiclesDTO allStarshipsDTOTemporary;

        if (dtoVehicleList.size() < count) {
            for (JSONObject json : results) {
                dtoVehicleList.add((DTOVehicles) Injector.injectDTOGeneric((String) json.get("url")));
            }

            if (next != null) {
                allStarshipsDTOTemporary = (AllVehiclesDTO) Injector.injectDTOGeneric(getNext());

                for (JSONObject json : allStarshipsDTOTemporary.getResults()) {
                    dtoVehicleList.add((DTOVehicles) Injector.injectDTOGeneric((String) json.get("url")));
                }

                allStarshipsDTOTemporary = (AllVehiclesDTO) Injector.injectDTOGeneric(allStarshipsDTOTemporary.getNext());


//                while (allPeopleDTOTemporary.getNext() != null) {

                while (true) {


                    for (JSONObject json : allStarshipsDTOTemporary.getResults()) {
                        dtoVehicleList.add((DTOVehicles) Injector.injectDTOGeneric((String) json.get("url")));
                    }

                    if (allStarshipsDTOTemporary.getNext() == null) {
                        break;
                    }

                    allStarshipsDTOTemporary = (AllVehiclesDTO) Injector.injectDTOGeneric(allStarshipsDTOTemporary.getNext());


                }

            }
        }

        return dtoVehicleList;

    }
}
