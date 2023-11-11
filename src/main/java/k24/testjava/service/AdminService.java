package k24.testjava.service;

import k24.testjava.model.Member;
import k24.testjava.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired private AdminRepository repo;

    public List<Member> listAll() {
        return (List<Member>) repo.findAll();
    }

    public void save(Member member){
        repo.save(member);
    }

    public Member get(Integer id) throws AdminNotFoundException {
        Optional<Member> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new AdminNotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Integer id) throws AdminNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new AdminNotFoundException("Could not find any users with ID " + id);
        }
        repo.deleteById(id);
    }
 }
