package com.puppies.service;

import com.puppies.domain.Weight;

import java.util.List;

public interface WeightService {

    Weight addWeighing(int puppyId, double weight);

    List<Weight> readWeightByPuppyId(int puppyId);

    void deleteWeight(int weightId);

}
