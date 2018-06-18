package com.sg.powerball.DAO;


import com.sg.powerball.DTO.Numbers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerBallRepository extends JpaRepository<Numbers, Integer> {
        
    @Query("SELECT n FROM Numbers n where n.pickId = :pickId")
     Numbers findByPickId(@Param("pickId") int pickId);
     
     @Query("select n from Numbers n where (:firstName = '' OR n.firstName = :firstName) and " +
                "(:lastName = '' OR n.lastName = :lastName) and (:eMail = '' OR n.eMail = :eMail)" +
                "and (:stateName = '' OR n.stateName = :stateName) and  (:pickId = 0 OR n.pickId = :pickId)")
     List<Numbers> findNumbers(@Param("pickId") int pickId, @Param("firstName") String firstName, 
                                                    @Param("lastName") String lastName, @Param("eMail") String eMail, 
                                                    @Param("stateName") String stateName);
     
     @Query("select n from Numbers n where n.active = :active")
     List<Numbers> findActive(@Param("active") boolean active);
     
     
     @Modifying
     @Query("UPDATE Numbers n SET n.active = :active where n.pickId = :pickId")
     void updateActive(@Param("pickId") int pickId, @Param("active") boolean active );
}
