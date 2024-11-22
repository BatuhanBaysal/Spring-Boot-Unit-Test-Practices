package com.batuhan.courseProject;

import com.batuhan.courseProject.models.CollegeStudent;
import com.batuhan.courseProject.models.GradebookCollegeStudent;
import com.batuhan.courseProject.repository.StudentDao;
import com.batuhan.courseProject.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class GradeBookControllerTests {
	private static MockHttpServletRequest request;

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StudentDao studentDao;

	@Mock
	private StudentAndGradeService studentCreateServiceMock;

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

	@BeforeAll
	public static void setup() {
		request = new MockHttpServletRequest();
		request.setParameter("firstname", "Batuhan");
		request.setParameter("lastname", "Baysal");
		request.setParameter("emailAddress", "batuhanbaysal3@gmail.com");
	}

	@BeforeEach
	public void beforeEach() {
		jdbc.execute(sqlAddStudent);
		jdbc.execute(sqlAddMathGrade);
		jdbc.execute(sqlAddScienceGrade);
		jdbc.execute(sqlAddHistoryGrade);
	}

	@AfterEach
	public void afterEach() {
		jdbc.execute(sqlDeleteStudent);
		jdbc.execute(sqlDeleteMathGrade);
		jdbc.execute(sqlDeleteScienceGrade);
		jdbc.execute(sqlDeleteHistoryGrade);
	}

	@Test
	public void getStudentHttpRequest() throws Exception {
		CollegeStudent studentOne = new GradebookCollegeStudent("Ali", "Veli", "ali.veli@gmail.com");
		CollegeStudent studentTwo = new GradebookCollegeStudent("Batuhan", "Baysal", "batuhanbaysal3@gmail.com");
		List<CollegeStudent> collegeStudentList = new ArrayList<>(Arrays.asList(studentOne, studentTwo));

		when(studentCreateServiceMock.getGradebook()).thenReturn(collegeStudentList);
		assertIterableEquals(collegeStudentList, studentCreateServiceMock.getGradebook());

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		ModelAndViewAssert.assertViewName(mav, "index");
	}

	@Test
	public void createStudentHttpRequest() throws Exception {
		CollegeStudent studentOne = new GradebookCollegeStudent("Ali", "Veli", "ali.veli@gmail.com");
		List<CollegeStudent> collegeStudentList =new ArrayList<>(Arrays.asList(studentOne));
		when(studentCreateServiceMock.getGradebook()).thenReturn(collegeStudentList);
		assertIterableEquals(collegeStudentList, studentCreateServiceMock.getGradebook());

		MvcResult mvcResult = this.mockMvc.perform(post("/")
				.contentType(MediaType.APPLICATION_JSON)
				.param("firstname", request.getParameterValues("firstname"))
				.param("lastname", request.getParameterValues("lastname"))
				.param("emailAddress", request.getParameterValues("emailAddress")))
				.andExpect(status().isOk()).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();
		ModelAndViewAssert.assertViewName(mav, "index");

		CollegeStudent verifyStudent = studentDao.findByEmailAddress("batuhanbaysal3@gmail.com");
		assertNotNull(verifyStudent, "Student should be found");
	}

	@Test
	public void deleteStudentHttpRequest() throws Exception {
		assertTrue(studentDao.findById(1).isPresent());

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.get("/delete/student/{id}", 1))
				.andExpect(status().isOk()).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();
		ModelAndViewAssert.assertViewName(mav, "index");
		assertFalse(studentDao.findById(1).isPresent());
	}

	@Test
	public void deleteStudentHttpRequestErrorPage() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.get("/delete/student/{id}", 0))
				.andExpect(status().isOk()).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();
		ModelAndViewAssert.assertViewName(mav, "error");
	}
}