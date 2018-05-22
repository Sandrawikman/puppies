package com.puppies.service;

import com.puppies.domain.Puppy;
import com.puppies.domain.Weight;
import com.puppies.repository.PuppyRepository;
import com.puppies.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuppyServiceImpl implements PuppyService {

    private PuppyRepository puppyRepository;
    private WeightRepository weightRepository;

    @Autowired
    public PuppyServiceImpl(PuppyRepository puppyRepository, WeightRepository weightRepository) {
        this.puppyRepository = puppyRepository;
        this.weightRepository = weightRepository;
    }

    @Override
    public Puppy createPuppy(int litterId, String name, String gender) {
        return puppyRepository.createPuppy(litterId, name, gender);
    }

    @Override
    public Puppy readPuppy(int puppyId) {
        Puppy puppy = puppyRepository.readPuppy(puppyId);
        Weight weight = weightRepository.readLatest(puppyId);
        puppy.setWeight(weight.getWeight());
        return puppy;
    }

    @Override
    public List<Puppy> readPuppiesByLitterId(int litterId) {
        List<Puppy> puppies = puppyRepository.readPuppiesByLitterId(litterId);
        for (Puppy puppy : puppies) {
            Weight weight = weightRepository.readLatest(puppy.getPuppyId());
            puppy.setWeight(weight.getWeight());
        }
        return puppies;
    }

    @Override
    public void updatePuppy(int puppyId, int litterId, String name, String gender) {
        puppyRepository.updatePuppy(puppyId, litterId, name, gender);
    }

    @Override
    public void deletePuppy(int puppyId) {
        puppyRepository.deletePuppy(puppyId);
    }
}
