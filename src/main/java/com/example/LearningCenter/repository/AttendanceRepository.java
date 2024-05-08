package com.example.LearningCenter.repository;

import com.example.LearningCenter.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query(nativeQuery = true, value= """
            select * from attendance where student_id=?1 and date>=?2 and date<=?3
            """)
    List<Attendance> findByStudentIdAndDateBetween(Long studentId, Date startDate, Date endDate);

    @Query(nativeQuery = true, value= """
            select * from attendance a
            inner join student s on s.id=a.student_id
            where s.group_id=?1 and a.date>=?2 and a.date<=?3
            """)
    List<Attendance> findByGroupIdAndDateBetween(Long studentId, Date startDate, Date endDate);

    List<Attendance> findByUserGroup_IdAndDateBetween(Long groupId, Date startDate, Date toDate);

}
