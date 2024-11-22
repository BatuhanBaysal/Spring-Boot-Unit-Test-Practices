package com.batuhan.courseProject.repository;

import com.batuhan.courseProject.models.ScienceGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScienceGradesDao extends CrudRepository<ScienceGrade, Integer> {
    public Iterable<ScienceGrade> findGradeByStudentId(int id);
    public void deleteByStudentId(int id);
}