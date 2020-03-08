package com.gmail.supersonicleader.service.constant;

public interface DocumentDTOConstant {

    int MIN_DESCRIPTION_SIZE = 3;
    int MAX_DESCRIPTION_SIZE = 100;
    String DESCRIPTION_REGEXP = "^[\\w\\d ,.]*$";

    String NOT_NULL_DESCRIPTION_MESSAGE = "Description cannot be empty";
    String DESCRIPTION_SIZE_MESSAGE = "Description length should be from 3 to 100";
    String DESCRIPTION_REGEXP_MESSAGE = "Description can contain only letters, digits, spaces and punctuation marks, like commas and dots";

}
