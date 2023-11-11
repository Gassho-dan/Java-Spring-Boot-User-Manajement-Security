package k24.testjava.repository;

import k24.testjava.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {
    public Users findByUsername(String username);
}
