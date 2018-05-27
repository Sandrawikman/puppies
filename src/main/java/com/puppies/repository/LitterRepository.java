package com.puppies.repository;
import com.puppies.domain.Litter;
import java.sql.Date;
import java.util.List;

public interface LitterRepository {

    int createLitter(int userId, String litterName, Date dateOfBirth);
    List<Litter> getLitterList(int userId);




//    Litter readLitter(int litterId);
//

//
//    void updateLitter(int litterId, int userId, String litterName, Date dateOfBirth);
//    void deleteLitter(int litterId);


}
