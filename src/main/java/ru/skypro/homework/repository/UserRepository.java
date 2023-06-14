package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;


import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findFirstByUsername(String username);

    ru.skypro.homework.model.User findByUsername(String name);

    Integer getUserById(String name);

    String getUserByFirstName(Integer id);

    String getAvatarUserById(Integer userId);

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "select user_profile_id from profile_user where email like ?1")
    Integer getUserProfileId(String name);
}
