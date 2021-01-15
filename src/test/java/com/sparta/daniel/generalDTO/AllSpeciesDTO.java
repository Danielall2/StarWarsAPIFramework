package com.sparta.daniel.generalDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.daniel.dto.DTOSpecies;
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

public class AllSpeciesDTO extends ParentDTO {

    List<DTOSpecies> dtoSpeciesList = new ArrayList<>();
    @JsonProperty("count")
    private int count;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private String previous;
    @JsonProperty("results")
    private List<JSONObject> results;

    public AllSpeciesDTO() {
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

    public List<DTOSpecies> getAllSpeciesAsListDTOs() {
        AllSpeciesDTO allSpeciesDTOTemporary;

        if (dtoSpeciesList.size() < count) {
            for (JSONObject json : results) {
                dtoSpeciesList.add((DTOSpecies) Injector.injectDTOGeneric((String) json.get("url")));
            }

            if (next != null) {
                allSpeciesDTOTemporary = (AllSpeciesDTO) Injector.injectDTOGeneric(getNext());

                for (JSONObject json : allSpeciesDTOTemporary.getResults()) {
                    dtoSpeciesList.add((DTOSpecies) Injector.injectDTOGeneric((String) json.get("url")));
                }

                allSpeciesDTOTemporary = (AllSpeciesDTO) Injector.injectDTOGeneric(allSpeciesDTOTemporary.getNext());


//                while (allPeopleDTOTemporary.getNext() != null) {

                while (true) {


                    for (JSONObject json : allSpeciesDTOTemporary.getResults()) {
                        dtoSpeciesList.add((DTOSpecies) Injector.injectDTOGeneric((String) json.get("url")));
                    }

                    if (allSpeciesDTOTemporary.getNext() == null) {
                        break;
                    }

                    allSpeciesDTOTemporary = (AllSpeciesDTO) Injector.injectDTOGeneric(allSpeciesDTOTemporary.getNext());


                }

            }
        }

        return dtoSpeciesList;

    }
}
