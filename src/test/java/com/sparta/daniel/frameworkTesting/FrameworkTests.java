package com.sparta.daniel.frameworkTesting;

import com.sparta.daniel.connector.ConnectionManager;
import com.sparta.daniel.dto.*;
import com.sparta.daniel.generalDTO.*;
import com.sparta.daniel.injector.Injector;
import org.junit.jupiter.api.*;

import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FrameworkTests {

    static DTOFilm dtoFilm;
    static DTOPeople dtoPeople;
    static DTOPlanets dtoPlanets;
    static DTOSpecies dtoSpecies;
    static DTOStarships dtoStarships;
    static DTOVehicles dtoVehicles;

    static DTOPeople dtoPeopleTwo;

    static AllFilmsDTO allFilmsDTO;
    static AllPeopleDTO allPeopleDTO;
    static AllPlanetsDTO allPlanetsDTO;
    static AllSpeciesDTO allSpeciesDTO;
    static AllStarshipsDTO allStarshipsDTO;
    static AllVehiclesDTO allVehiclesDTO;

    @BeforeAll
    static void setup() {
        dtoFilm = (DTOFilm) Injector.injectDTOGeneric(ConnectionManager.getConnection("films/2/"));
        dtoPeople = (DTOPeople) Injector.injectDTOGeneric(ConnectionManager.getConnection("people/1/"));
        dtoPlanets = (DTOPlanets) Injector.injectDTOGeneric(ConnectionManager.getConnection("planets/4/"));
        dtoSpecies = (DTOSpecies) Injector.injectDTOGeneric(ConnectionManager.getConnection("species/3/"));
        dtoStarships = (DTOStarships) Injector.injectDTOGeneric(ConnectionManager.getConnection("starships/9/"));
        dtoVehicles = (DTOVehicles) Injector.injectDTOGeneric(ConnectionManager.getConnection("vehicles/4/"));


        dtoPeopleTwo = (DTOPeople) Injector.injectDTOGeneric(ConnectionManager.getConnection("people/11/"));

        // General DTOs
        allFilmsDTO = (AllFilmsDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("films/"));
        allPeopleDTO = (AllPeopleDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("people/"));
        allPlanetsDTO = (AllPlanetsDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("planets/"));
        allSpeciesDTO = (AllSpeciesDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("species/"));
        allStarshipsDTO = (AllStarshipsDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("starships/"));
        allVehiclesDTO = (AllVehiclesDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("vehicles/"));

    }

    @Test
    @DisplayName("Example of using methods inside multiple DTOs")
    void usingMultipleDTOs() {

        System.out.println();
        System.out.println("Person of interest : " + dtoPeople.getName());

        System.out.println();
        System.out.println(dtoPeople.getName() + " has been in the following films, and these films have the following planets : ");

        for (DTOFilm film : dtoPeople.getFilmsAsDTOs()) {
            System.out.println();
            System.out.println("---Film : " + film.getTitle() + "---");
            for (DTOPlanets planet : film.getPlanetsListAsDTOs()) {
                System.out.println(planet.getName());
            }
            System.out.println();
        }
    }

    @Test
    @DisplayName("Example of using methods inside multiple DTOs for different character")
    void usingMultipleDTOsForDifferentCharacter() {

        System.out.println();
        System.out.println("Person of interest : " + dtoPeopleTwo.getName());

        System.out.println();
        System.out.println(dtoPeopleTwo.getName() + " has been in the following films, and these films have the following planets : ");

        for (DTOFilm film : dtoPeopleTwo.getFilmsAsDTOs()) {
            System.out.println();
            System.out.println("---Film : " + film.getTitle() + "---");
            for (DTOPlanets planet : film.getPlanetsListAsDTOs()) {
                System.out.println(planet.getName());
            }
            System.out.println();
        }
    }


    @Nested
    @DisplayName("Testing Connection Manager connection")
    class testingConnectionManagerConnection {

        @Test
        @DisplayName("Testing Status Code is 200")
        void testConnection() {
            Assertions.assertEquals(200, ConnectionManager.getStatusCode("people/1/"));
        }

        @Test
        @DisplayName("Testing Headers")
        void testHeaders() {
            Map<String, List<String>> headers = ConnectionManager.getHeaders("people/1/").map();
            System.out.println(headers);
        }

        @Test
        @DisplayName("Test Server")
        void testServer() {
            Map<String, List<String>> headers = ConnectionManager.getHeaders("people/1/").map();
            String serverFromHeaders = headers.get("Server").get(0);
            String server  = "nginx/1.16.1";
            Assertions.assertEquals(serverFromHeaders, server);
        }

    }

    @Nested
    @DisplayName("Testing whether all DTOs have been populated for one specific URL")
    class testPopulatingData {

        @Test
        void testPopulatingDataFilms() {
            Assertions.assertEquals("The Empire Strikes Back", dtoFilm.getTitle());
        }

        @Test
        void testPopulatingDataPeople() {
            Assumptions.assumeTrue(dtoPeople.getName().equals("Luke Skywalker"));
            Assertions.assertEquals("Luke Skywalker", dtoPeople.getName());
        }

        @Test
        void testPopulatingDataPlanets() {
            Assertions.assertEquals("Hoth", dtoPlanets.getName());
        }

        @Test
        void testPopulatingDataSpecies() {
            Assertions.assertEquals("Wookie", dtoSpecies.getName());
        }

        @Test
        void testPopulatingDataStarships() {
            Assertions.assertEquals("Death Star", dtoStarships.getName());
        }

        @Test
        void testPopulatingDataVehicles() {
            Assertions.assertEquals("Sand Crawler", dtoVehicles.getName());
        }

    }

    @Nested
    @DisplayName("Test of DTOPeople Luke Skywalker methods returning DTOs")
    class testingDTOPeopleMethodsReturningDTOs {

        // The assumptions and expected values are inherent for Luke Skywalker
        // Most of these tests fail if the DTOPeople is altered, but the methods to acquiring the data are still be valid.

        @Test
        void testHomeworld() {
            Assumptions.assumeTrue(dtoPeople.getName().equals("Luke Skywalker"));
            Assertions.assertEquals("Tatooine", dtoPeople.getHomeWorldAsDTOPlanet().getName());
        }

        @Test
        void testGetListOfDTOFilms() {
            Assumptions.assumeTrue(dtoPeople.getName().equals("Luke Skywalker"));
            Assertions.assertEquals("A New Hope", dtoPeople.getFilmsAsDTOs().get(0).getTitle());
        }

        @Test
        void testGetListOfDTOSpecies() {
            Assumptions.assumeTrue(dtoPeople.getName().equals("Luke Skywalker"));
            Assertions.assertEquals(0, dtoPeople.getSpeciesAsListDTOs().size());
        }

        @Test
        void testGetListOfDTOStarships() {
            Assumptions.assumeTrue(dtoPeople.getName().equals("Luke Skywalker"));
            Assertions.assertEquals("X-wing", dtoPeople.getStarshipsAsListDTOs().get(0).getName());
        }

        @Test
        void testGetListOfDTOVehicles() {
            Assumptions.assumeTrue(dtoPeople.getName().equals("Luke Skywalker"));
            Assertions.assertEquals("360", dtoPeople.getVehiclesAsListDTOs().get(1).getMax_atmosphering_speed());
        }

    }

    @Nested
    @DisplayName("Testing converting Strings to Date and Time Methods in DTOPeople")
    class testingConvertDateMethods {

        @Test
        @DisplayName("Test convert created to LocalDate")
        void testCreatedLocalDate() {
            Assertions.assertEquals(LocalDate.of(2014, 12, 9), dtoPeople.getCreatedLocalDate());
        }

        @Test
        @DisplayName("Test convert edited to LocalDate")
        void testEditedLocalDate() {
            Assertions.assertEquals(LocalDate.of(2014, 12, 20), dtoPeople.getEditedLocalDate());
        }

    }

    @Nested
    @DisplayName("Testing Parent methods")
    class testingParentMethods {

        @Test
        @DisplayName("Testing number of words in String method")
        void testNumberOfWordsInString() {
            int numberOfWords = dtoPeople.checkNumberOfWordsInString(dtoPeople.getName());
            System.out.println();
            System.out.println("Number of words in name : " + numberOfWords);
            Assertions.assertTrue(numberOfWords > 0);
        }

        @Test
        @DisplayName("Testing capital letter for each name checker")
        void testCapitalLetterForEachName() {
            Assertions.assertTrue(dtoPeople.checkIfAllNamesStartWithCapitalLetter(dtoPeople.getName()));
        }

        @Test
        @DisplayName("Testing Star Wars date formatting check")
        void testStarWarsDateFormatEnd() {
            Assertions.assertTrue(dtoPeople.checkWhetherStarWarsDateFormattedProperly("297ABY"));

        }

        @Test
        @DisplayName("Testing that Star Wars date starts with number")
        void testStarWarsDateFormatStart() {
            System.out.println();
            System.out.println("I expect NumberFormatException in this test (see below) : ");
            Assertions.assertFalse(dtoPeople.checkWhetherStarWarsDateFormattedProperly("297BBYABY"));
        }
    }

    @Nested
    @DisplayName("Getting Names of everything")
    class testingGetAll {

        @Test
        @DisplayName("Get all Films")
        void getAllFilms() {
            List<DTOFilm> dtoFilmList = allFilmsDTO.getAllFilmsAsListDTOs();
            for (DTOFilm film : dtoFilmList) {
                System.out.println(film.getTitle());
            }

            Assertions.assertEquals(allFilmsDTO.getCount(), dtoFilmList.size());
        }

        @Test
        @DisplayName("Get all People")
        void getAllPeople() {
            List<DTOPeople> dtoPeopleArrayList = allPeopleDTO.getAllPeopleAsListDTOs();
            for (DTOPeople person : dtoPeopleArrayList) {
                System.out.println(person.getName());
            }

            Assertions.assertEquals(allPeopleDTO.getCount(), dtoPeopleArrayList.size());
        }

        @Test
        @DisplayName("Get all Planets")
        void getAllPlanets() {
            List<DTOPlanets> dtoPlanetsList = allPlanetsDTO.getAllPlanetsAsListDTOs();
            for (DTOPlanets planet : dtoPlanetsList) {
                System.out.println(planet.getName());
            }

            Assertions.assertEquals(allPlanetsDTO.getCount(), dtoPlanetsList.size());
        }

        @Test
        @DisplayName("Get all Species")
        void getAllSpecies() {
            List<DTOSpecies> dtoSpeciesList = allSpeciesDTO.getAllSpeciesAsListDTOs();
            for (DTOSpecies specimen : dtoSpeciesList) {
                System.out.println(specimen.getName());
            }

            Assertions.assertEquals(allSpeciesDTO.getCount(), dtoSpeciesList.size());
        }

        @Test
        @DisplayName("Get all Starships")
        void getAllStarships() {
            List<DTOStarships> dtoStarshipsList = allStarshipsDTO.getAllStarshipsAsListDTOs();
            for (DTOStarships starship : dtoStarshipsList) {
                System.out.println(starship.getName());
            }

            Assertions.assertEquals(allStarshipsDTO.getCount(), dtoStarshipsList.size());
        }

        @Test
        @DisplayName("Get all Vehicles")
        void getAllVehicles() {
            List<DTOVehicles> dtoVehiclesList = allVehiclesDTO.getAllVehiclesAsListDTOs();
            for (DTOVehicles vehicle : dtoVehiclesList) {
                System.out.println(vehicle.getName());
            }

            Assertions.assertEquals(allVehiclesDTO.getCount(), dtoVehiclesList.size());
        }
    }

}
