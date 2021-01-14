package com.sparta.daniel.injector;

import com.sparta.daniel.dto.*;
import com.sparta.daniel.generalDTO.*;

public class Factory {

    private static final String INITIAL_URL = "https://swapi.dev/api/";
    private static final String END_PATTERN_ONE = "(\\/[0-9])\\/";
    private static final String END_PATTERN_TWO = "([0-9]+)\\/";


    public static ParentDTO dtoFactory(int n)
    {
        switch(n)
        {
            case 1:
                return new DTOPeople();

            case 2:
                return new DTOFilm();

            case 3:
                return new DTOPlanets();

            case 4:
                return new DTOSpecies();

            case 5:
                return new DTOStarships();

            case 6:
                return new DTOVehicles();

            case 7:
                return new AllPeopleDTO();

            case 8:
                return new AllFilmsDTO();

            case 9:
                return new AllPlanetsDTO();

            case 10:
                return new AllSpeciesDTO();

            case 11:
                return new AllStarshipsDTO();

            case 12:
                return new AllVehiclesDTO();
        }
        return null;
    }

    // Not really a factory but want this here

    public static int dtoNumberCheck(String url) {
        String substring = url.substring(url.length() - 3);
//        System.out.println(substring);

        if (substring.matches(END_PATTERN_ONE) || substring.matches(END_PATTERN_TWO)) {
            if (url.contains("people")) {
                return 1;
            } else if (url.contains("films")) {
                return 2;
            } else if (url.contains("planets")) {
                return 3;
            } else if (url.contains("species")) {
                return 4;
            } else if (url.contains("starships")) {
                return 5;
            } else if (url.contains("vehicles")) {
                return 6;
            } else {
                return 0;
            }
        } else {
            if (url.contains("people")) {
                return 7;
            } else if (url.contains("films")) {
                return 8;
            } else if (url.contains("planets")) {
                return 9;
            } else if (url.contains("species")) {
                return 10;
            } else if (url.contains("starships")) {
                return 11;
            } else if (url.contains("vehicles")) {
                return 12;
            } else {
                return 0;
            }

        }
    }


}
