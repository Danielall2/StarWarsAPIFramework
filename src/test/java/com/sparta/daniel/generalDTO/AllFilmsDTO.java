package com.sparta.daniel.generalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.daniel.dto.DTOFilm;
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

public class AllFilmsDTO extends ParentDTO {

    @JsonProperty("count")
    private int count;

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private String previous;

    @JsonProperty("results")
    private List<JSONObject> results;

    public AllFilmsDTO() {
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


    public List<DTOFilm> getAllFilmsAsListDTOs() {
        List<DTOFilm> dtoFilmList = new ArrayList<>();

        for (JSONObject json : results) {
            dtoFilmList.add((DTOFilm) Injector.injectDTOGeneric((String) json.get("url")));
        }

        return dtoFilmList;

    }
}
