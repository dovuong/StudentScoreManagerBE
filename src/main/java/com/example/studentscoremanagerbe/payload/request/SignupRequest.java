package com.example.studentscoremanagerbe.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@SuppressWarnings("checkstyle:Indentation")
public class SignupRequest {
    @NotBlank
    @Size(max = 50)
    private String username;
    @NotBlank
    @Size(min = 8, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
