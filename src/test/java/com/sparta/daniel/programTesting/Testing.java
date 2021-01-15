package com.sparta.daniel.programTesting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.daniel.connector.ConnectionManager;
import com.sparta.daniel.dto.DTOPeople;
import com.sparta.daniel.dto.DTOSpecies;
import com.sparta.daniel.dto.DTOStarships;
import com.sparta.daniel.generalDTO.*;
import com.sparta.daniel.injector.Injector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Testing {

    static AllFilmsDTO allFilmsDTO;
    static AllPeopleDTO allPeopleDTO;
    static AllPlanetsDTO allPlanetsDTO;
    static AllSpeciesDTO allSpeciesDTO;
    static AllStarshipsDTO allStarshipsDTO;
    static AllVehiclesDTO allVehiclesDTO;
    private static ObjectMapper objectMapper;
    private static DTOPeople dtoPeople;
    private static HttpResponse<String> httpResponse = null;
    private static HttpHeaders httpHeaders;
    private static JsonNode jsonNode;

    public static boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        dtoPeople = new DTOPeople();

        httpResponse = ConnectionManager.getConnectionResponse(ConnectionManager.getConnection("people/1/"));
        httpHeaders = httpResponse.headers();

        dtoPeople = (DTOPeople) Injector.injectDTOGeneric(ConnectionManager.getConnection("people/1/"));

        allFilmsDTO = (AllFilmsDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("films/"));
        allPeopleDTO = (AllPeopleDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("people/"));
        allPlanetsDTO = (AllPlanetsDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("planets/"));
        allSpeciesDTO = (AllSpeciesDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("species/"));
        allStarshipsDTO = (AllStarshipsDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("starships/"));
        allVehiclesDTO = (AllVehiclesDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("vehicles/"));


    }


    @Test
    @DisplayName("Does the 'Death Star' exist as a starship?")
    void doesTheDeathStarExistAsAStarship() {
        List<DTOStarships> starships = allStarshipsDTO.getAllStarshipsAsListDTOs();
        List<String> starshipNames = new ArrayList<>();

        for (DTOStarships starship : starships) {
            starshipNames.add(starship.getName());
        }
        Assertions.assertTrue(starshipNames.contains("Death Star"));
    }

    @Test
    @DisplayName("Luke Skywalker did not fly The Death Star")
    void didLukeFlyDeathStar() {
        List<DTOStarships> lukeSkywalkerStarships = dtoPeople.getStarshipsAsListDTOs();
        List<String> nameOfLukeStarships = new ArrayList<>();
        for (DTOStarships dtoStarships : lukeSkywalkerStarships) {
            nameOfLukeStarships.add(dtoStarships.getName());
        }

        Assertions.assertFalse(nameOfLukeStarships.contains("Death Star"));

    }

    @Test
    @DisplayName("Luke Skywalker did fly an X Wing")
    void didLukeFlyXWing() {
        List<DTOStarships> lukeSkywalkerStarships = dtoPeople.getStarshipsAsListDTOs();
        List<String> nameOfLukeStarships = new ArrayList<>();
        for (DTOStarships dtoStarships : lukeSkywalkerStarships) {
            nameOfLukeStarships.add(dtoStarships.getName());
            //System.out.println(dtoStarships.getName());
        }

        Assertions.assertTrue(nameOfLukeStarships.contains("X-wing"));

    }

    @Test
    @DisplayName("Chewbacca is a Wookie")
    void chewbaccaIsAWookie() {
        List<DTOPeople> everyone = allPeopleDTO.getAllPeopleAsListDTOs();
        String heightOfChewbacca = "";
        List<DTOSpecies> speciesOfChewbacca = null;

        String name = "Chewbacca";

        System.out.println();
        System.out.println("---Everyone in Star Wars---");

        for (DTOPeople person : everyone) {

            System.out.println(person.getName());

            if (person.getName().equals(name)) {

                heightOfChewbacca = person.getHeight();

                speciesOfChewbacca = person.getSpeciesAsListDTOs();

            }
        }

        System.out.println();
        System.out.println("Height of " + name + " : " + heightOfChewbacca);
        try {
            System.out.println("Species of " + name + " : " + speciesOfChewbacca.get(0).getName());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("No Species found for this character");
        }

    }

}
