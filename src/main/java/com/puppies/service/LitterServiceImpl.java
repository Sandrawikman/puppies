package com.puppies.service;
import com.puppies.domain.Litter;
import com.puppies.repository.LitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LitterServiceImpl implements LitterService {

    private LitterRepository litterRepository;

    @Autowired
    public LitterServiceImpl(LitterRepository litterRepository) {
        this.litterRepository = litterRepository;
    }

    @Override
    public int createLitter(int userId, String litterName, Date dateOfBirth) {
        return litterRepository.createLitter(userId, litterName, (java.sql.Date) dateOfBirth);
    }

    @Override
    public List<Litter> getLitterList(int userId) {
        return litterRepository.getLitterList(userId);
    }

//    public int createLitter(int userId, String litterName, java.sql.Date dateOfBirth){
//        return litterRepository.createLitter(userId, litterName, dateOfBirth);
//    }

//    @Override
//    public Litter readLitter(int litterId) {
//        Litter litter = litterRepository.readLitter(litterId);
//        return litter;
//    }
//
//    @Override
//    public List<Litter> readLitterByUserId(int userId) {
//        return null;
//    }

//    @Override     //TODO update litter
//    public void updateLitter(int litterId, int userId, String litterName, Date dateOfBirth) {
//        litterRepository.updateLitter(litterId, userId, litterName, dateOfBirth);
//    }

//    @Override     //TODO delete litter
//    public void deleteLitter(int litterId) {
//        LitterRepository.deleteLitter(litterId);
//    }
    }
