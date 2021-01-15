package com.sparta.daniel.generalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.daniel.dto.DTOStarships;
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

public class AllStarshipsDTO extends ParentDTO {

    List<DTOStarships> dtoStarshipList = new ArrayList<>();
    @JsonProperty("count")
    private int count;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private String previous;
    @JsonProperty("results")
    private List<JSONObject> results;

    public AllStarshipsDTO() {
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


//    public List<DTOStarships> getAllStarshipsAsListDTOs() {
//
//
//        for (JSONObject json : results) {
//            dtoStarshipList.add((DTOStarships) Injector.injectDTOGeneric((String) json.get("url")));
//        }
//
//        return dtoStarshipList;
//
//    }

    public List<DTOStarships> getAllStarshipsAsListDTOs() {
        AllStarshipsDTO allStarshipsDTOTemporary;

        if (dtoStarshipList.size() < count) {
            for (JSONObject json : results) {
                dtoStarshipList.add((DTOStarships) Injector.injectDTOGeneric((String) json.get("url")));
            }

            if (next != null) {
                allStarshipsDTOTemporary = (AllStarshipsDTO) Injector.injectDTOGeneric(getNext());

                for (JSONObject json : allStarshipsDTOTemporary.getResults()) {
                    dtoStarshipList.add((DTOStarships) Injector.injectDTOGeneric((String) json.get("url")));
                }

                allStarshipsDTOTemporary = (AllStarshipsDTO) Injector.injectDTOGeneric(allStarshipsDTOTemporary.getNext());


//                while (allPeopleDTOTemporary.getNext() != null) {

                while (true) {


                    for (JSONObject json : allStarshipsDTOTemporary.getResults()) {
                        dtoStarshipList.add((DTOStarships) Injector.injectDTOGeneric((String) json.get("url")));
                    }

                    if (allStarshipsDTOTemporary.getNext() == null) {
                        break;
                    }

                    allStarshipsDTOTemporary = (AllStarshipsDTO) Injector.injectDTOGeneric(allStarshipsDTOTemporary.getNext());


                }

            }
        }

        return dtoStarshipList;

    }
}
