package com.puppies.service;

import com.puppies.domain.Weight;
import com.puppies.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightServiceImpl implements WeightService {

    private WeightRepository weightRepository;

    @Autowired
    public WeightServiceImpl(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    @Override
    public Weight addWeighing(int puppyId, double weight) {
        return weightRepository.createWeight(puppyId, weight);
    }

    @Override
    public List<Weight> readWeightByPuppyId(int puppyId) {
        return weightRepository.readWeightsByPuppyId(puppyId);
    }

    @Override
    public void deleteWeight(int weightId) {
        weightRepository.deleteWeight(weightId);
    }
}
