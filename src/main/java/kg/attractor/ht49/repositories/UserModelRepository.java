package kg.attractor.ht49.repositories;

import kg.attractor.ht49.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel,Long> {

    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<UserModel> findByPhoneNumber(String phone);

    Optional<UserModel> findByResetPasswordToken(String token);

}
