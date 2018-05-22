package com.puppies.service;

import com.puppies.domain.Puppy;

import java.util.List;

public interface PuppyService {

    Puppy createPuppy(int litterId, String name, String gender);

    Puppy readPuppy(int puppyId);

    List<Puppy> readPuppiesByLitterId(int litterId);

    void updatePuppy(int puppyId, int litterId, String name, String gender);

    void deletePuppy(int puppyId);

}
