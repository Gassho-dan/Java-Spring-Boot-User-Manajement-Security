package k24.testjava.model;

import javax.persistence.*;

@Entity
@Table(name = "users_members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 200)
    private String nama;
    @Column(nullable = false, length = 200)
    private String password;
    @Column(nullable = false,  length = 14)
    private String no_hp;
    @Column(nullable = false, length = 8)
    private String tgl_lahir;
    @Column(nullable = false,  length = 200)
    private String email;
    @Column(nullable = false, length = 6)
    private String jk;
    @Column(nullable = false, unique = true, length = 16)
    private String no_ktp;
    @Column(nullable = false, unique = true, length = 200)
    private String foto;

    public Member(String nama, String password, String no_hp, String tgl_lahir, String email, String jk, String no_ktp, String foto) {
        this.nama = nama;
        this.password = password;
        this.no_hp = no_hp;
        this.tgl_lahir = tgl_lahir;
        this.email = email;
        this.jk = jk;
        this.no_ktp = no_ktp;
        this.foto = foto;
    }

    public Member() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", password='" + password + '\'' +
                ", no_hp='" + no_hp + '\'' +
                ", tgl_lahir='" + tgl_lahir + '\'' +
                ", email='" + email + '\'' +
                ", jk='" + jk + '\'' +
                ", no_ktp='" + no_ktp + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
