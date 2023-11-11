package k24.testjava.helper;

import k24.testjava.model.Member;
import k24.testjava.model.Users;
import k24.testjava.repository.AdminRepository;
import k24.testjava.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class DatabaseLoader {
    private UserRepository userRepo;
    private AdminRepository adminRepo;

    public DatabaseLoader(UserRepository userRepo, AdminRepository adminRepo) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
    }

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            Users user1 = new Users(2,"david", "david123");
            Users user2 = new Users(3,"ravi", "ravi123");

            userRepo.saveAll(List.of(user1,user2));

            Member member1 = new Member("qwerty","qwerty123","123456789012","1997-01-01","qwerty@gmail.com","L","123456789009876","oke.png");

            adminRepo.saveAll(List.of(member1));

            System.out.println("Database initialized");
        };
    }
}
