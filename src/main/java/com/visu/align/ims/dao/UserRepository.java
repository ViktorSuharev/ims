package com.visu.align.ims.dao;

import com.visu.align.ims.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Access to the user data.
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Find a user by username
	 *
	 * @param username the user's username
	 * @return {@link User} with specified name.
	 */
	User findOneByUsername(String username);

}
