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

    public List<DTOFilm> dtoFilmList = new ArrayList<>();
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
        AllFilmsDTO allFilmsDTOTemporary;
//        List<DTOFilm> dtoFilmList = new ArrayList<>();

//        if (next == null) {
//            return dtoFilmList;
//        }


        if (dtoFilmList.size() < count) {
            for (JSONObject json : results) {
                dtoFilmList.add((DTOFilm) Injector.injectDTOGeneric((String) json.get("url")));
            }

            if (next != null) {
                allFilmsDTOTemporary = (AllFilmsDTO) Injector.injectDTOGeneric(getNext());

                for (JSONObject json : allFilmsDTOTemporary.getResults()) {
                    dtoFilmList.add((DTOFilm) Injector.injectDTOGeneric((String) json.get("url")));
                }

                allFilmsDTOTemporary = (AllFilmsDTO) Injector.injectDTOGeneric(getNext());

                while (allFilmsDTOTemporary.getNext() != null) {

                    for (JSONObject json : allFilmsDTOTemporary.getResults()) {
                        dtoFilmList.add((DTOFilm) Injector.injectDTOGeneric((String) json.get("url")));
                    }

                    allFilmsDTOTemporary = (AllFilmsDTO) Injector.injectDTOGeneric(allFilmsDTOTemporary.getNext());

                }

            }
        }

        return dtoFilmList;

    }
}
