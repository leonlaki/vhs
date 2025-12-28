package vhslab.VHS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vhslab.VHS.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
