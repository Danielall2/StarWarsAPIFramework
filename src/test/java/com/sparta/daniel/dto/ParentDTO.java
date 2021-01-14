package com.sparta.daniel.dto;

import java.util.Arrays;
import java.util.InputMismatchException;

public abstract class ParentDTO {

    public ParentDTO() {
    }


    public int checkNumberOfWordsInString(String string) {
        String[] words = string.split("\\\\s|,|\\\\.|-");
        return words.length;
    }

    public boolean checkWhetherStarWarsDateFormattedProperly(String starWarsDate) {
        boolean trueOrFalse = false;
        int number = -5;

        try {
            number = Integer.parseInt(starWarsDate.substring(0, starWarsDate.length() - 3));
        } catch (NumberFormatException e) {
            System.out.println("Expected Number but found NumberFormatException");
            return false;
        }
        String endOfDate = starWarsDate.substring(starWarsDate.length()-3, starWarsDate.length());
        return (endOfDate.equals("BBY") || endOfDate.equals("ABY")) && (number > 0);
    }


}
