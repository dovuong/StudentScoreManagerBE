package com.example.studentscoremanagerbe.payload.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * Some javadoc. // OK
 *
 * @author Linh
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
public class FacultyRequest {
    @NotNull(message = "This is password")
    @ApiModelProperty(notes = "password", example = "adminserver", required = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
