package com.puppies.service;

import com.puppies.domain.Litter;
import com.puppies.repository.LitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LitterServiceImpl implements LitterService {

    private LitterRepository litterRepository;

    @Autowired
    public LitterServiceImpl(LitterRepository litterRepository) {
        this.litterRepository = litterRepository;
    }

    @Override
    public Litter createLitter(int userId, String name, Date dateOfBirth) {
        return litterRepository.createLitter(userId, name, dateOfBirth);
    }

    @Override
    public Litter litter(int userId, String name, Date dateOfBirth) {
        return null;
    }

    @Override
    public Litter readLitter(int litterId) {
        Litter litter = litterRepository.readLitter(litterId);
        return litter;
    }

//    @Override
//    public List<Litter> readLitterByUserId(int userId) {
//        List<Litter> litters = LitterRepository.readLitterByUserId(userId);
//        return litters;
//    }

    @Override
    public void updateLitter(int litterId, int userId, String name, Date dateOfBirth) {
        litterRepository.updateLitter(litterId, userId, name, dateOfBirth);
    }

//    @Override
//    public void deleteLitter(int litterId) {
//        LitterRepository.deleteLitter(litterId);
//    }
}
