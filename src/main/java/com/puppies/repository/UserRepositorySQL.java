package com.puppies.repository;

import com.puppies.exception.RepositoryExceptions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class UserRepositorySQL implements UserRepository {

    private DataSource dataSource;

    @Autowired
    public UserRepositorySQL(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int createUser(String username, String password, String email) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO user(username, password, email) " +
                     " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, BCrypt.hashpw(password, BCrypt.gensalt())); //krypterar lösenordet i databasen
            ps.setString(3, email);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int userId = -1;
            while (rs.next()) {
                userId = rs.getInt(1);
            }
            return userId;
        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in createUser - UserRepository", e);
        }
    }

    @Override
    public void updateUser(int userId, String username, String password, String email) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE user SET (username = ?, password = ?, email = ?) " +
                     " WHERE (idUser = ?)")) {
            ps.setString(1, username);
            ps.setString(2, BCrypt.hashpw(password, BCrypt.gensalt())); //krypterar lösenordet i databasen
            ps.setString(3, email);
            ps.setInt(4, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in updateUser - UserRepository", e);
        }
    }

    @Override
    public void deleteUser(int userId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE user WHERE (idUser = ?)")) {
            ps.setInt(1, userId);
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RepositoryExceptions("something went wrong in deleteUser - UserRepository", e);
        }
    }

    @Override
    public Integer checkLogin(String username, String password) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT iduser, username, password FROM user WHERE (username = ?)")) {
            ps.setString(1, username);
            ResultSet results = ps.executeQuery();

            if (results.next()) {
                String hashedPassword = results.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    return results.getInt("iduser");
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("something went wrong in checkLogin - UserRepository", e);
        }
    }

    @Override
    public boolean usernameAlreadyTaken(String username) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT username FROM user WHERE (username = ?)")) {
            ps.setString(1, username);
            ResultSet results = ps.executeQuery();
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("something went wrong in checkLogin - UserRepository", e);
        }
        return true;
    }
}
