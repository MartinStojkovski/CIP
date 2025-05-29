package com.example.CIP_Final_Exam.Repository;
import com.example.CIP_Final_Exam.Entity.Stops;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface StopsRepository extends JpaRepository<Stops, Long>{
    List<Stops> findByCode(int code);
    List<Stops> findByName(String name);

}
