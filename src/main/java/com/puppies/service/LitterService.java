package com.puppies.service;
import com.puppies.domain.Litter;
import java.util.Date;
import java.util.List;

public interface LitterService {

    int createLitter(int userId, String litterName, Date dateOfBirth);
    List<Litter> getLitterList(int userId);





//    Litter readLitter(int litterId);
//    void updateLitter(int litterId, int userId, String litterName, Date dateOfBirth);
//    void deleteLitter(int litterId); Fungerar inte riktigt än av någon superkonstig anledning

}
