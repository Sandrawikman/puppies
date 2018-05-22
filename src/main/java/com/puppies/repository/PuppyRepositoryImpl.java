package com.puppies.repository;

import com.puppies.domain.Puppy;
import com.puppies.exception.RepositoryExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PuppyRepositoryImpl implements PuppyRepository {

    private DataSource dataSource;

    @Autowired
    public PuppyRepositoryImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Puppy createPuppy(int litterId, String puppyName, String puppyGender) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO puppy(idLitter, Name, Gender) " +
                     " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, litterId);
            ps.setString(2, puppyName);
            ps.setString(3, puppyGender);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int puppyId = -1;
            while (rs.next()) {
                puppyId = rs.getInt(1);
            }
            return new Puppy(puppyId, litterId, puppyName, puppyGender, null);
        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in createPuppy - PuppyRepository", e);
        }
    }

    @Override
    public Puppy readPuppy(int puppyId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT idPuppy, idLitter, Name, Gender FROM puppy WHERE (idPuppy = ?)")) {
            ps.setInt(1, puppyId);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();
            Puppy puppy = null;
            while (rs.next()) {
                puppy = new Puppy(
                        rs.getInt("idPuppy"),
                        rs.getInt("idLitter"),
                        rs.getString("Name"),
                        rs.getString("Gender"),
                        null);
            }
            return puppy;
        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in readPuppy - PuppyRepository", e);
        }
    }

    @Override
    public List<Puppy> readPuppiesByLitterId(int litterId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT idPuppy, idLitter, Name, Gender FROM puppy WHERE (idLitter = ?) ")) {
            ps.setInt(1, litterId);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();
            List<Puppy> puppies = new ArrayList<>();
            while (rs.next()) {
                puppies.add(new Puppy(
                        rs.getInt("idPuppy"),
                        rs.getInt("idLitter"),
                        rs.getString("Name"),
                        rs.getString("Gender"),
                        null));
            }
            return puppies;
        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in readPuppiesByLitterId - PuppyRepository", e);
        }
    }

    @Override
    public void updatePuppy(int puppyId, int litterId, String name, String gender) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE puppy SET (idLitter = ?, Name = ?, Gender = ?) WHERE (idPuppy = ?)")) {

            ps.setInt(1, litterId);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setInt(4, puppyId);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in updatePuppy - PuppyRepository", e);
        }
    }

    @Override
    public void deletePuppy(int puppyId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM puppy WHERE (idPuppy = ?)")) {
            ps.setInt(1, puppyId);
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in deletePuppy - PuppyRepository", e);
        }
    }
}
