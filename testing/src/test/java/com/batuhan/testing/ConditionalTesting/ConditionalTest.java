package com.batuhan.testing.ConditionalTesting;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConditionalTest {

    @Test
    @Disabled("Dont run!!!") // @Disabled is an annotation used in JUnit 5 to temporarily disable the execution of a test or test class.
    void basicTest() {
        System.out.println("@Disabled command on basicTest.");
    }

    @Test
    @EnabledOnOs(OS.WINDOWS) // @EnabledOnOs is a JUnit 5 annotation used to run tests on a specific operating system.
    void testForWindowsOnly() {
        System.out.println("@EnabledOnOs command for Windows.");
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testForMacOnly() {
        System.out.println("@EnabledOnOs command for Mac.");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void testForLinuxOnly() {
        System.out.println("@EnabledOnOs command for Linux.");
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    void testForMacAndWindows() {
        System.out.println("@EnabledOnOs command for Mac and Windows.");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17) // @EnabledOnJre is an annotation used in JUnit 5 to run tests on a specific Java Runtime Environment (JRE) version.
    void testForOnlyJava17() {
        System.out.println("@EnabledOnJre command for Only JAVA 17.");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_13)
    void testForOnlyJava13() {
        System.out.println("@EnabledOnJre command for Only JAVA 17.");
    }

    @Test
    @EnabledForJreRange(min=JRE.JAVA_13, max=JRE.JAVA_18) // @EnabledForJreRange is an annotation used in JUnit 5 to run tests within a specific JRE version range.
    void testForJavaRange() {
        System.out.println("@EnabledForJreRange command for min. JAVA 13, max. JAVA 18.");
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "BATUHAN_ENV", matches = "DEV") // @EnabledIfEnvironmentVariable is an annotation in JUnit 5 that allows a test to be run based on the value of a specific environment variable.
    // Modify Run Configuretion... Environment variables: BATUHAN_ENV=DEV
    void testOnlyForDevEnvironment() {
        System.out.println("@EnabledIfEnvironmentVariable command.");
    }

    @Test
    @EnabledIfSystemProperty(named = "BATUHAN_SYS_PROP", matches = "CI_CD_DEPLOY") // @EnabledIfSystemProperty is an annotation in JUnit 5 that enables a test to be run based on the value of a specific system property.
    // Modify Run Configuretion... -ea -DBATUHAN_SYS_PROP=CI_CD_DEPLOY
    void testOnlyForSystemProperty() {
        System.out.println("@EnabledIfSystemProperty command.");
    }
}