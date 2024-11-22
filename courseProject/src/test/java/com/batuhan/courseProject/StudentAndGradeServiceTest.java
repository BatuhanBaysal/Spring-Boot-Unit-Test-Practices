package com.batuhan.courseProject;

import com.batuhan.courseProject.models.*;
import com.batuhan.courseProject.repository.HistoryGradesDao;
import com.batuhan.courseProject.repository.MathGradesDao;
import com.batuhan.courseProject.repository.ScienceGradesDao;
import com.batuhan.courseProject.repository.StudentDao;
import com.batuhan.courseProject.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MathGradesDao mathGradesDao;

    @Autowired
    private ScienceGradesDao scienceGradesDao;

    @Autowired
    private HistoryGradesDao historyGradesDao;

    @Value("${sql.script.create.student}")
    private String sqlAddStudent;

    @Value("${sql.script.create.math.grade}")
    private String sqlAddMathGrade;

    @Value("${sql.script.create.science.grade}")
    private String sqlAddScienceGrade;

    @Value("${sql.script.create.history.grade}")
    private String sqlAddHistoryGrade;

    @Value("${sql.script.delete.student}")
    private String sqlDeleteStudent;

    @Value("${sql.script.delete.math.grade}")
    private String sqlDeleteMathGrade;

    @Value("${sql.script.delete.science.grade}")
    private String sqlDeleteScienceGrade;

    @Value("${sql.script.delete.history.grade}")
    private String sqlDeleteHistoryGrade;

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlAddStudent);
        jdbc.execute(sqlAddMathGrade);
        jdbc.execute(sqlAddScienceGrade);
        jdbc.execute(sqlAddHistoryGrade);
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeleteStudent);
        jdbc.execute(sqlDeleteMathGrade);
        jdbc.execute(sqlDeleteScienceGrade);
        jdbc.execute(sqlDeleteHistoryGrade);
    }

    @Test
    public void createStudentService() {
        studentService.createStudent("Batuhan", "Baysal", "batuhanbaysal3@gmail.com");
        CollegeStudent student = studentDao.findByEmailAddress("batuhanbaysal3@gmail.com");

        assertEquals("batuhanbaysal3@gmail.com", student.getEmailAddress(), "find by email");
    }

    @Test
    public void isStudentNullCheck() {
        assertTrue(studentService.checkIfStudentIsNull(1));
        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Test
    public void deleteStudentService() {
        Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);
        Optional<MathGrade> deletedMathGrade = mathGradesDao.findById(1);
        Optional<ScienceGrade> deletedScienceGrade = scienceGradesDao.findById(1);
        Optional<HistoryGrade> deletedHistoryGrade = historyGradesDao.findById(1);

        assertTrue(deletedCollegeStudent.isPresent(), "Return true");
        assertTrue(deletedMathGrade.isPresent());
        assertTrue(deletedScienceGrade.isPresent());
        assertTrue(deletedHistoryGrade.isPresent());

        studentService.deleteStudent(1);
        deletedCollegeStudent = studentDao.findById(1);
        deletedMathGrade = mathGradesDao.findById(1);
        deletedScienceGrade = scienceGradesDao.findById(1);
        deletedHistoryGrade = historyGradesDao.findById(1);

        assertFalse(deletedCollegeStudent.isPresent(), "Return false");
        assertFalse(deletedMathGrade.isPresent());
        assertFalse(deletedScienceGrade.isPresent());
        assertFalse(deletedHistoryGrade.isPresent());
    }

    @Sql("/insertData.sql")
    @Test
    public void getGradeBookService() {
        Iterable<CollegeStudent> studentIterable = studentService.getGradebook();
        List<CollegeStudent> collegeStudents = new ArrayList<>();

        for (CollegeStudent collegeStudent : studentIterable) {
            collegeStudents.add(collegeStudent);
        }

        assertEquals(6, collegeStudents.size());
    }

    @Test
    public void createGradeService() {
        assertTrue(studentService.createGrade(80.50, 1, "math"));
        assertTrue(studentService.createGrade(80.50, 1, "science"));
        assertTrue(studentService.createGrade(80.50, 1, "history"));

        Iterable<MathGrade> mathGrades = mathGradesDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades = scienceGradesDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradesDao.findGradeByStudentId(1);

        assertTrue(((Collection<MathGrade>) mathGrades).size() == 1, "Student has math grades");
        assertTrue(((Collection<ScienceGrade>) scienceGrades).size() == 1, "Student has science grades");
        assertTrue(((Collection<HistoryGrade>) historyGrades).size() == 1, "Student has history grades");
    }

    @Test
    public void createGradeServiceReturnFalse() {
        assertFalse(studentService.createGrade(105,1,"math"));
        assertFalse(studentService.createGrade(-5,1,"math"));
        assertFalse(studentService.createGrade(80.50,2,"math"));
        assertFalse(studentService.createGrade(80.50,1,"literature"));
    }

    @Test
    public void deleteGradeService() {
        assertEquals(0, studentService.deleteGrade(0, "math"), "Returns student id after delete");
        assertEquals(0, studentService.deleteGrade(0, "science"), "Returns student id after delete");
        assertEquals(0, studentService.deleteGrade(0, "history"), "Returns student id after delete");
    }

    @Test
    public void deleteGradeServiceReturnStudentIdOfZero() {
        assertEquals(0, studentService.deleteGrade(0, "science"), "No student should have 0 id");
        assertEquals(0, studentService.deleteGrade(1, "literature"), "No student should have a literature class");
    }

    @Test
    public void studentInformation() {
        GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(1);

        assertNotNull(gradebookCollegeStudent);
        assertEquals(1, gradebookCollegeStudent.getId());
        assertEquals("Ali", gradebookCollegeStudent.getFirstname());
        assertEquals("Veli", gradebookCollegeStudent.getLastname());
        assertEquals("ali.veli@gmail.com", gradebookCollegeStudent.getEmailAddress());

        assertTrue(gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size() == 1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size() == 1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size() == 1);
    }

    @Test
    public void studentInformationServiceReturnNull() {
        GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(0);
        assertNull(gradebookCollegeStudent);
    }
}