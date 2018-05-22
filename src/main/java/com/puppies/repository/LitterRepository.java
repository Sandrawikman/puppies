package com.puppies.repository;

import com.puppies.domain.Litter;

import java.sql.Date;
import java.util.List;

public interface LitterRepository {

    Litter createLitter(int userId, String name, Date dateOfBirth);

    Litter createLitter(int userId, String name, java.util.Date dateOfBirth);

    Litter readLitter(int litterId);

    List<Litter> readLitterByUserId(int userId);

    void updateLitter(int litterId, int userId, String name, java.util.Date dateOfBirth);

    List<Litter> readLittersByUserId(int userId);

    void deleteLitter(int litterId);





}
