package database.model;

import javax.persistence.*;

@Entity
@Table(name = "client", schema = "public", catalog = "veterinary_clinic")
public class ClientEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "first_name", nullable = false, length = -1)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = -1)
    private String lastName;
    @Basic
    @Column(name = "tin", nullable = false, length = -1)
    private String tin;
    @Basic
    @Column(name = "phone_number", nullable = false, length = -1)
    private String phoneNumber;
    @Basic
    @Column(name = "email", nullable = false, length = -1)
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
