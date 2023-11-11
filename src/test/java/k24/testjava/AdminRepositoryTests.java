package k24.testjava;

import k24.testjava.model.Member;
import k24.testjava.repository.AdminRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AdminRepositoryTests {
    @Autowired private AdminRepository repo;

    @Test
    public void testAddNew(){
        Member member = new Member();
        member.setNama("Test3");
        member.setPassword("test123");
        member.setNo_hp("123456789012");
        member.setTgl_lahir("2023-10-10");
        member.setEmail("test@gmail.com");
        member.setJk("L");
        member.setNo_ktp("1234567890123456");
        member.setFoto("Test3.png");

        Member saveMember = repo.save(member);

        Assertions.assertThat(saveMember).isNotNull();
        Assertions.assertThat(saveMember.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Member> members = repo.findAll();
        Assertions.assertThat(members).hasSizeGreaterThan(0);

        for (Member member: members) {
            System.out.println(member);
        }
    }

    @Test
    public void testUpdate(){
        Integer memberId = 1;
        Optional<Member> optionalMember = repo.findById(memberId);
        Member member = optionalMember.get();
        member.setPassword("welcome123");
        repo.save(member);

        Member memberUpdate = repo.findById(memberId).get();
        Assertions.assertThat(memberUpdate.getPassword()).isEqualTo("welcome123");
    }

    @Test
    public void testGet(){
        Integer memberId = 3;
        Optional<Member> optionalMember = repo.findById(memberId);
        Assertions.assertThat(optionalMember).isPresent();
        System.out.println(optionalMember.get());
    }

    @Test
    public void testDelete(){
        Integer memberId = 5;
        repo.deleteById(memberId);

        Optional<Member> optionalMember = repo.findById(memberId);
        Assertions.assertThat(optionalMember).isNotPresent();
    }

    @Test
    public void testFIndUserByEmail() {
        String email = "ca@gmail.com";

        Member member = repo.findByEmail(email);
        Assertions.assertThat(member).isNotNull();
    }

}
