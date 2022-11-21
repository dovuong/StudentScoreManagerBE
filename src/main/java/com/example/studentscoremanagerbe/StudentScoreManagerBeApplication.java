/**
 * Info about this package doing something for package-info.java file.
 */

package com.example.studentscoremanagerbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */

@EnableSwagger2
@SpringBootApplication
public class StudentScoreManagerBeApplication {

    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @param args ada
     * @since 20/11/2022
     * @deprecated Some javadoc.
     */
    public static void main(final String[] args) {
        SpringApplication.run(StudentScoreManagerBeApplication.class, args);
    }
}
