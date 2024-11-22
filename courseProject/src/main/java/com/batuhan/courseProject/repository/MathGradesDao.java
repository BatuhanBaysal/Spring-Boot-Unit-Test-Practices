package com.batuhan.courseProject.repository;

import com.batuhan.courseProject.models.MathGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathGradesDao extends CrudRepository<MathGrade, Integer> {
    public Iterable<MathGrade> findGradeByStudentId(int id);
    public void deleteByStudentId(int id);
}