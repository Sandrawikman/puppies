package com.puppies.service;

import com.puppies.domain.Litter;

import java.util.Date;
import java.util.List;

public interface LitterService {

    Litter createLitter(int userId, String name, Date dateOfBirth); //Ska denna vara här

    Litter readLitter(int litterId);

    List<Litter> readLitterByUserId(int userId);

    void updateLitter(int litterId, int userId, String name, Date dateOfBirth);

    void deleteLitter(int litterId);

}
