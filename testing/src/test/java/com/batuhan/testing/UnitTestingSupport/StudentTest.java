package com.batuhan.testing.UnitTestingSupport;

import com.batuhan.testing.UnitTestingSupport.models.CollegeStudent;
import com.batuhan.testing.UnitTestingSupport.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class StudentTest {
    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach() {
        count += 1;
        System.out.println("Testing: " + appInfo + " which is " + appDescription + " Version: " + appVersion +
                ". Execution of test method " + count);

        student.setFirstname("Batuhan");
        student.setLastname("Baysal");
        student.setEmailAddress("batuhanbaysal3@gmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 92.0, 87.0, 75.0)));
        student.setStudentGrades(studentGrades);
    }

    @Test
    @DisplayName("Add Grade Equals Result")
    public void testAddGradeResults() {
        assertEquals(354.0, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades()
                .getMathGradeResults()));
    }

    @Test
    @DisplayName("Add Grade Not Equals Result")
    public void testAddGradeNotEqualsResults() {
        assertNotEquals(334.0, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades()
                .getMathGradeResults()));
    }

    @Test
    @DisplayName("Is Grade Greater True")
    public void testIsGradeGreaterTrue() {
        assertTrue(studentGrades.isGradeGreater(90, 75), "failure - should be true.");
    }

    @Test
    @DisplayName("Is Grade Greater False")
    public void testIsGradeGreaterFalse() {
        assertFalse(studentGrades.isGradeGreater(87, 96), "failure - should be false.");
    }

    @Test
    @DisplayName("Check Null")
    public void testCheckNull() {
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()),
                "object should not be null.");
    }

    @Test
    @DisplayName("Create Student without Grade Init")
    public void testCreateStudentWithoutGradeInit() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstname("Ali");
        studentTwo.setLastname("Veli");
        studentTwo.setEmailAddress("aliveli@gmail.com");
        assertNotNull(studentTwo.getFirstname());
        assertNotNull(studentTwo.getLastname());
        assertNotNull(studentTwo.getEmailAddress());
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));
    }

    @Test
    @DisplayName("Verify students are prototypes")
    public void testVerifyStudentsArePrototypes() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        assertNotSame(student, studentTwo);
    }

    @Test
    @DisplayName("Find Grade Point Average")
    public void testVFindGradePointAverage() {
        assertAll("Testing all assertEquals",
                () -> assertEquals(354.0, studentGrades.addGradeResultsForSingleClass(student
                        .getStudentGrades().getMathGradeResults())),
                () -> assertEquals(88.5, studentGrades.findGradePointAverage(student
                        .getStudentGrades().getMathGradeResults()))
        );
    }
}