package dao.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * The interface User mapper.
 * Manage the mapper between User and xml.
 *
 * @author Chenyi He
 */
public interface UserMapper {
    /**
     * Add user boolean.
     *
     * @param user the user
     */
    public void addUser(@Param("user") User user);
    /**
     * Delete user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean deleteUser(@Param("user") User user);

    /**
     * Update user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean updateUser(@Param("user") User user);

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    public User getUserById(@Param("id") int userId);


    public User getUserByName(@Param("name") String name);

    public void setVIP(@Param("name") String name);
}
