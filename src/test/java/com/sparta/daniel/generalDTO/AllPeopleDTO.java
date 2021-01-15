package com.sparta.daniel.generalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.daniel.dto.DTOPeople;
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

public class AllPeopleDTO extends ParentDTO {

    public List<DTOPeople> dtoPeopleList = new ArrayList<>();
    @JsonProperty("count")
    private int count;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private String previous;
    @JsonProperty("results")
    private List<JSONObject> results;

    public AllPeopleDTO() {
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


    public List<DTOPeople> getAllPeopleAsListDTOs() {
        AllPeopleDTO allPeopleDTOTemporary;
//        List<DTOPeople> dtoPeopleList = new ArrayList<>();

//        for (JSONObject json : results) {
//            dtoPeopleList.add((DTOPeople) Injector.injectDTOGeneric((String) json.get("url")));
//        }

        if (dtoPeopleList.size() < count) {
            for (JSONObject json : results) {
                dtoPeopleList.add((DTOPeople) Injector.injectDTOGeneric((String) json.get("url")));
            }

            if (next != null) {
                allPeopleDTOTemporary = (AllPeopleDTO) Injector.injectDTOGeneric(getNext());

                for (JSONObject json : allPeopleDTOTemporary.getResults()) {
                    dtoPeopleList.add((DTOPeople) Injector.injectDTOGeneric((String) json.get("url")));
                }

                allPeopleDTOTemporary = (AllPeopleDTO) Injector.injectDTOGeneric(allPeopleDTOTemporary.getNext());


//                while (allPeopleDTOTemporary.getNext() != null) {

                while (true) {


                    for (JSONObject json : allPeopleDTOTemporary.getResults()) {
                        dtoPeopleList.add((DTOPeople) Injector.injectDTOGeneric((String) json.get("url")));
                    }

                    if (allPeopleDTOTemporary.getNext() == null) {
                        break;
                    }

                    allPeopleDTOTemporary = (AllPeopleDTO) Injector.injectDTOGeneric(allPeopleDTOTemporary.getNext());


                }

            }
        }

        return dtoPeopleList;

    }
}
