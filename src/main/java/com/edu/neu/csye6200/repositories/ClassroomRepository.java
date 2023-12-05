package com.edu.neu.csye6200.repositories;

import com.edu.neu.csye6200.model.ClassSections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassroomRepository extends JpaRepository<ClassSections, Integer> {

    ClassSections findTopByMinAgeBeforeAndMaxAgeAfterOrderByClassRoomId(int age1, int age2);

    ClassSections findTopByStudentIdsContaining(String studentId);

    //long countAllByTeacherIdsContainingAndClassRuleIdNotLike(String teacherId, int classRuleId);
}
