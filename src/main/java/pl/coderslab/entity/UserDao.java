package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, passwd) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, passwd = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users where id = ?";
    private static final String FINDALL_USER_QUERY =
            "SELECT * FROM users";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User newuser) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newuser.getUserName());
            statement.setString(2, newuser.getEmail());
            statement.setString(3, hashPassword(newuser.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                newuser.setId(resultSet.getInt(1));
            }
            return newuser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("passwd"));
                return user;
            } else {
                System.out.println("Brak szukanego id w bazie danych");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
       return null;
    }

    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Błąd modyfikacji rekordu." + e.getErrorCode());
            //e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement =
                    conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd modyfikacji rekordu." + e.getErrorCode());
            //e.printStackTrace();
        }
    }
    public static User[] findAll() {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement =
                    conn.prepareStatement(FINDALL_USER_QUERY);
            ResultSet resultSet = statement.executeQuery();
            User[] allUsers = new User[0];
            while ( resultSet.next() ) {

                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("passwd"));
                allUsers = addToArray(user, allUsers);

            }
            return allUsers;
        } catch (SQLException e) {
            System.out.println("Błąd modyfikacji rekordu." + e.getErrorCode());
            //e.printStackTrace();
            return null;
        }

    }

    private static User[] addToArray(User u, User[] users) {

        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;

        return tmpUsers;

    }

}
