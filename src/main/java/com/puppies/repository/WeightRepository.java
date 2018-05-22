package com.puppies.repository;

import com.puppies.domain.Weight;

import java.util.List;

public interface WeightRepository {

    Weight createWeight(int puppyId, double weight);

    List<Weight> readWeightsByPuppyId(int puppyId);

    Weight readLatest(int puppyId);

    void deleteWeight(int weightId);

}
