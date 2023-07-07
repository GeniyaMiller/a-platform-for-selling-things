package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;


import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    int countByEmail(String email);


    User getUserById(String name);





    @Query(nativeQuery = true, value = "select user_profile_id from profile_user where email like ?1")
    Integer getUserProfileId(String name);

    Optional<User> findByEmail(String name);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
