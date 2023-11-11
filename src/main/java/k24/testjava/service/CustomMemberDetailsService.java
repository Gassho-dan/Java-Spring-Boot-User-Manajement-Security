package k24.testjava.service;

import k24.testjava.model.CustomUserDetails;
import k24.testjava.model.CustumMemberDetails;
import k24.testjava.model.Member;
import k24.testjava.model.Users;
import k24.testjava.repository.AdminRepository;
import k24.testjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomMemberDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = repo.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException("No user found with the given username");
        }

        return new CustumMemberDetails(member);
    }
}
