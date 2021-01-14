package com.sparta.daniel.frameworkTesting;

import com.sparta.daniel.connector.ConnectionManager;
import com.sparta.daniel.dto.*;
import com.sparta.daniel.generalDTO.*;
import com.sparta.daniel.injector.Injector;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class FrameworkTests {
    DTOFilm dtoFilm;
    DTOPeople dtoPeople;
    DTOPlanets dtoPlanets;
    DTOSpecies dtoSpecies;
    DTOStarships dtoStarships;
    DTOVehicles dtoVehicles;

    AllFilmsDTO allFilmsDTO;
    AllPeopleDTO allPeopleDTO;
    AllPlanetsDTO allPlanetsDTO;
    AllSpeciesDTO allSpeciesDTO;
    AllStarshipsDTO allStarshipsDTO;
    AllVehiclesDTO allVehiclesDTO;

    @BeforeEach
    void setup() {
        dtoFilm = (DTOFilm) Injector.injectDTOGeneric(ConnectionManager.getConnection("films/2/"));
        dtoPeople = (DTOPeople) Injector.injectDTOGeneric(ConnectionManager.getConnection("people/1/"));
        dtoPlanets = (DTOPlanets) Injector.injectDTOGeneric(ConnectionManager.getConnection("planets/4/"));
        dtoSpecies = (DTOSpecies) Injector.injectDTOGeneric(ConnectionManager.getConnection("species/3/"));
        dtoStarships = (DTOStarships) Injector.injectDTOGeneric(ConnectionManager.getConnection("starships/9/"));
        dtoVehicles = (DTOVehicles) Injector.injectDTOGeneric(ConnectionManager.getConnection("vehicles/4/"));

        // General DTOs
        allFilmsDTO = (AllFilmsDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("films/"));
        allPeopleDTO = (AllPeopleDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("people/"));
        allPlanetsDTO = (AllPlanetsDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("planets/"));
        allSpeciesDTO = (AllSpeciesDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("species/"));
        allStarshipsDTO = (AllStarshipsDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("starships/"));
        allVehiclesDTO = (AllVehiclesDTO) Injector.injectDTOGeneric(ConnectionManager.getConnection("vehicles/"));


    }


    @Nested
    @DisplayName("Testing Connection Manager connection")
    class testingConnectionManagerConnection {

        @Test
        @DisplayName("Testing Status Code is 200")
        void testConnection() {
            Assertions.assertEquals(200, ConnectionManager.getStatusCode("people/1/"));
        }

//        @Test
//        @DisplayName("Testing Headers")
//        void testHeaders() {
//            // Not really sure how to test headers generally before getting specific ones which the actual Tests will need to do
//            //System.out.println(ConnectionManager.getHeaders("people/1/").toString());
//        }

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
        // Most of these tests fail the the DTOPeople is altered, but the methods to aquiring the data should still be valid.

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
            Assertions.assertEquals(LocalDate.of(2014,12,9), dtoPeople.getCreatedLocalDate());
        }

        @Test
        @DisplayName("Test convert edited to LocalDate")
        void testEditedLocalDate() {
            Assertions.assertEquals(LocalDate.of(2014,12,20), dtoPeople.getEditedLocalDate());
        }

    }


    @Nested
    @DisplayName("Testing Parent methods")
    class testingParentMethods {

        @Test
        @DisplayName("Testing number of words in String method")
        void testNumberOfWordsInString() {

            int numberOfWords = dtoPeople.checkNumberOfWordsInString(dtoPeople.getName());
            System.out.println("");
            System.out.println("Number of words in name : " + numberOfWords);
            Assertions.assertTrue(numberOfWords > 0);
        }

        @Test
        @DisplayName("Testing Star Wars date formatting check")
        void testStarWarsDateFormatEnd() {
            Assertions.assertTrue(dtoPeople.checkWhetherStarWarsDateFormattedProperly("297ABY"));

        }

        @Test
        @DisplayName("Testing that Star Wars date starts with number")
        void testStarWarsDateFormatStart() {
            System.out.println("");
            System.out.println("I expect NumberFormatException in this test (see below) : ");
            Assertions.assertFalse(dtoPeople.checkWhetherStarWarsDateFormattedProperly("297BBYABY"));
        }
    }

    @Nested
    @DisplayName("Getting Names of everything (Not really a test")
    class testingGetAll {

        @Test
        @DisplayName("Get all Films")
        void getAllFilms() {
            for (DTOFilm film : allFilmsDTO.getAllFilmsAsListDTOs()) {
                System.out.println(film.getTitle());
            }
        }

        @Test
        @DisplayName("Get all People")
        void getAllPeople() {
            for (DTOPeople person : allPeopleDTO.getAllPeopleAsListDTOs()) {
                System.out.println(person.getName());
            }
        }

        @Test
        @DisplayName("Get all Planets")
        void getAllPlanets() {
            for (DTOPlanets planet : allPlanetsDTO.getAllPlanetsAsListDTOs()) {
                System.out.println(planet.getName());
            }
        }

        @Test
        @DisplayName("Get all Species")
        void getAllSpecies() {
            for (DTOSpecies specimen : allSpeciesDTO.getAllSpeciesAsListDTOs()) {
                System.out.println(specimen.getName());
            }
        }

        @Test
        @DisplayName("Get all Starships")
        void getAllStarships() {
            for (DTOStarships starship : allStarshipsDTO.getAllStarshipsAsListDTOs()) {
                System.out.println(starship.getName());
            }
        }

        @Test
        @DisplayName("Get all Vehicles")
        void getAllVehicles() {
            for (DTOVehicles vehicle : allVehiclesDTO.getAllVehiclesAsListDTOs()) {
                System.out.println(vehicle.getName());
            }
        }
    }




    @Test
    @DisplayName("Example of using methods inside multiple DTOs")
    void usingMultipleDTOs() {

        System.out.println("");
        System.out.println("Person of interest : " + dtoPeople.getName());

        System.out.println("");
        System.out.println(dtoPeople.getName() + " has been in the following films, and these films have the following planets : ");

        for (DTOFilm film : dtoPeople.getFilmsAsDTOs()) {
            System.out.println("");
            System.out.println("---Film : " + film.getTitle() + "---");
            for (DTOPlanets planet : film.getPlanetsListAsDTOs()) {
                System.out.println(planet.getName());
            }
            System.out.println("");
        }
    }

}
