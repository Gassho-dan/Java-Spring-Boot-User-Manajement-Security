package k24.testjava.repository;

import k24.testjava.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Member, Integer> {
    public Long countById(Integer id);

    public Member findByEmail(String email);
}
