package com.puppies.repository;

import com.puppies.domain.Puppy;

import java.util.List;

public interface PuppyRepository {

//    Parent createParent(int userId, String parentName, String parentGender);

    Puppy createPuppy(int litterId, String puppyName, String puppyGender);

    Puppy readPuppy(int puppyId);

    List<Puppy> readPuppiesByLitterId(int litterId);

    void updatePuppy(int puppyId, int litterId, String puppyName, String gender);

    void deletePuppy(int puppyId);

}
