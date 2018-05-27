package com.puppies.repository;

import com.puppies.domain.Weight;
import com.puppies.exception.RepositoryExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WeightRepositorySQL implements WeightRepository {

    private DataSource dataSource;

    @Autowired
    public WeightRepositorySQL(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Weight createWeight(int puppyId, double weight) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO weight(puppyId, weight, createdAt) " +
                     " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
//            Date date = Date.valueOf(LocalDate.EPOCH);
            ps.setInt(1, puppyId);
            ps.setDouble(2, weight);
//            ps.setDate(3, date);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int weightId = -1;
            while (rs.next()) {
                weightId = rs.getInt(1);
            }
            return new Weight(weightId, puppyId, weight, null);
        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in createWeight - WeightRepository", e);
        }
    }

    @Override
    public List<Weight> readWeightsByPuppyId(int puppyId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT idweight, puppyId, weight, createdAt FROM weight WHERE (puppyId = ?)")) {
            ps.setInt(1, puppyId);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();
            List<Weight> weights = new ArrayList<>();
            while (rs.next()) {
                weights.add(new Weight(
                        rs.getInt("idweight"),
                        rs.getInt("puppyId"),
                        rs.getDouble("weight"),
                        rs.getDate("createdAt")));
            }
            return weights;
        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in readWeightsByPuppyId - WeightRepository", e);
        }
    }

    @Override
    public Weight readLatest(int puppyId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT idweight, puppyId, weight, createdAt FROM weight WHERE createdAt = (" +
                             "SELECT MAX(createdAt) FROM weight WHERE puppyId = ?)")) {
            ps.setInt(1, puppyId);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();
            Weight weight = null;
            while (rs.next()) {
                weight = new Weight(
                        rs.getInt("idweight"),
                        rs.getInt("puppyId"),
                        rs.getDouble("weight"),
                        rs.getDate("weight_date"));
            }
            return weight;
        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in readLatest - WeightRepository", e);
        }
    }

    @Override
    public void deleteWeight(int weightId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM weight WHERE (idweight = ?)")) {
            ps.setInt(1, weightId);
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in deleteWeight - WeightRepository", e);
        }
    }
}
