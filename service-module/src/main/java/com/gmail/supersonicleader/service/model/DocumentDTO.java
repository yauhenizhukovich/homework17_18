package com.gmail.supersonicleader.service.model;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.gmail.supersonicleader.service.constant.DocumentDTOConstant.DESCRIPTION_REGEXP;
import static com.gmail.supersonicleader.service.constant.DocumentDTOConstant.DESCRIPTION_REGEXP_MESSAGE;
import static com.gmail.supersonicleader.service.constant.DocumentDTOConstant.DESCRIPTION_SIZE_MESSAGE;
import static com.gmail.supersonicleader.service.constant.DocumentDTOConstant.MAX_DESCRIPTION_SIZE;
import static com.gmail.supersonicleader.service.constant.DocumentDTOConstant.MIN_DESCRIPTION_SIZE;
import static com.gmail.supersonicleader.service.constant.DocumentDTOConstant.NOT_NULL_DESCRIPTION_MESSAGE;

public class DocumentDTO {

    private Long id;
    private UUID uniqueNumber;
    @NotNull(message = NOT_NULL_DESCRIPTION_MESSAGE)
    @Size(min = MIN_DESCRIPTION_SIZE, max = MAX_DESCRIPTION_SIZE, message = DESCRIPTION_SIZE_MESSAGE)
    @Pattern(regexp = DESCRIPTION_REGEXP, message = DESCRIPTION_REGEXP_MESSAGE)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(UUID uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
