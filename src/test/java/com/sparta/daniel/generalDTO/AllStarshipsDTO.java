package com.sparta.daniel.generalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.daniel.dto.DTOFilm;
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


    public List<DTOStarships> getAllStarshipsAsListDTOs() {
        List<DTOStarships> dtoStarshipList = new ArrayList<>();;

        for (JSONObject json : results) {
            dtoStarshipList.add((DTOStarships) Injector.injectDTOGeneric((String) json.get("url")));
        }

        return dtoStarshipList;

    }
}
