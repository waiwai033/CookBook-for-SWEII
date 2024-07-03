package dao.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * The interface UserMapper.
 * This interface manages the mapping between User objects and the corresponding
 *
 * @author Chenyi He
 */
public interface UserMapper {

    /**
     * Adds a new user to the database.
     *
     * @param user the user to be added
     */
    void addUser(@Param("user") User user);

    /**
     * Deletes an existing user from the database.
     *
     * @param user the user to be deleted
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteUser(@Param("user") User user);

    /**
     * Updates an existing user in the database.
     *
     * @param user the user to be updated
     * @return true if the update was successful, false otherwise
     */
    boolean updateUser(@Param("user") User user);

    /**
     * Retrieves a user from the database by their ID.
     *
     * @param userId the ID of the user to be retrieved
     * @return the user with the specified ID, or null if no such user exists
     */
    User getUserById(@Param("id") int userId);

    /**
     * Retrieves a user from the database by their name.
     *
     * @param name the name of the user to be retrieved
     * @return the user with the specified name, or null if no such user exists
     */
    User getUserByName(@Param("name") String name);

    /**
     * Sets a user as VIP in the database by their name.
     *
     * @param name the name of the user to be set as VIP
     */
    void setVIP(@Param("name") String name);
}
