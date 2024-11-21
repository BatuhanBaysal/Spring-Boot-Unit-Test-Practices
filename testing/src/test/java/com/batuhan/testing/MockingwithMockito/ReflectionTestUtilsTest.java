package com.batuhan.testing.MockingwithMockito;

import com.batuhan.testing.MockingwithMockito.models.CollegeStudent;
import com.batuhan.testing.MockingwithMockito.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ReflectionTestUtilsTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    public void studentBeforeEach() {
        studentOne.setFirstname("Batuhan");
        studentOne.setLastname("Baysal");
        studentOne.setEmailAddress("batuhanbaysal3@gmail.com");
        studentOne.setStudentGrades(studentGrades);

        ReflectionTestUtils.setField(studentOne, "id",1);
        ReflectionTestUtils.setField(studentOne,"studentGrades", new StudentGrades(new ArrayList<>(
                Arrays.asList(100.0, 92.0, 87.0, 75.0)))
        );
    }

    @Test
    @DisplayName("Private Field")
    public void getPrivateField() {
        assertEquals(1, ReflectionTestUtils.getField(studentOne, "id"));
    }

    @Test
    @DisplayName("Invoke Private Method")
    public void invokePrivateMethod() {
        assertEquals("Batuhan 1", ReflectionTestUtils.invokeMethod(studentOne, "getFirstNameAndId"),
                "Fail private method not call.");
    }
}