package com.puppies.repository;
import com.puppies.domain.Litter;
import com.puppies.exception.RepositoryExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LitterRepositorySQL implements LitterRepository {

    private DataSource dataSource;

    @Autowired
    public LitterRepositorySQL(
            @Qualifier("dataSource")
            DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int createLitter(int userId, String litterName, Date dateOfBirth) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO litter(LitterName, dateOfBirth, idUser) " +
                     " VALUES (?, ?, ?) ", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, litterName);
            ps.setDate(2, dateOfBirth);
            ps.setInt(3, userId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int litterId = -1;
            while (rs.next()) {
                litterId = rs.getInt(1);
            }
            return litterId;
        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in createLitter - LitterRepository", e);
        }
    }

    @Override
    public List<Litter> getLitterList(int userId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM litter " +
                     "WHERE idUser = ? ")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Litter> litterList = new ArrayList<>();
            while (rs.next()) {
                litterList.add(new Litter(
                        rs.getInt("idLitter"),
                        rs.getString("LitterName"),
                        rs.getDate("dateOfBirth")));
            }
            System.out.print("test1");
            return litterList;
        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in litterlist - Litter-Repository impl", e);
        }
    }


//    @Override
//    public Litter createLitter(int userId, String litterName, java.util.Date dateOfBirth) {
//        return null;
//    }

//    @Override
//    public Litter readLitter(int litterId) {
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT idLitter, LitterName, Birthday, idUser FROM litter WHERE idLitter = ?")) {
//            ps.setInt(1, litterId);
//            ps.executeQuery();
//
//            ResultSet rs = ps.getResultSet();
//            Litter litter = null;
//            while (rs.next()) {
//                litter = new Litter(
//                        rs.getInt("idLitter"),
//                        rs.getString("LitterName"),
//                        rs.getDate("birthday"));
//            }
//            return litter;
//        } catch (SQLException e) {
//            throw new RepositoryExceptions("something went wrong in readLitter - LitterRepository", e);
//        }
//    }
//
//    @Override
//    public List<Litter> readLitterByUserId(int userId) {
//        return null;
//    }

//    @Override     //TODO read litter
//    public List<Litter> readLittersByUserId(int userId) {
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT idLitter, LitterName, Birthday, idUser FROM litter WHERE idUser = ?")) {
//            ps.setInt(1, userId);
//            ps.executeQuery();
//
//            ResultSet rs = ps.getResultSet();
//            List<Litter> litters = new ArrayList<>();
//            while (rs.next()) {
//                litters.add(new Litter(
//                        rs.getInt("idLitter"),
//                        rs.getString("LitterName"),
//                        rs.getDate("birthday")));
//            }
//            return litters;
//        } catch (SQLException e) {
//            throw new RepositoryExceptions("something went wrong in readLittersByUserId - LitterRepository", e);
//        }
//    }

//    @Override     //TODO update litter
//    public void updateLitter(int litterId, int userId, String litterName, Date dateOfBirth) {
//        return null;
//    }
//    @Override     //TODO delete litter
//    public void deleteLitter(int litterId) {
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement ps = conn.prepareStatement("DELETE FROM litter WHERE idLitter = ?")) {
//            ps.setInt(1, litterId);
//            ps.executeQuery();
//
//        } catch (SQLException e) {
//            throw new RepositoryExceptions("something went wrong in deleteLitter - LitterRepository", e);
//        }
//    }
}
