package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;

    private int id;
    private String email;
    private String phone;

    public Contact() {
    }

    public Contact(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public Contact(int id, String email, String phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id
            && Objects.equals(email, contact.email)
            && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phone);
    }

    @Override
    public String toString() {
        return "Contact{"
            + "id=" + id
            + ", email='" + email + '\''
            + ", phoneNumber='" + phone + '\''
            + '}';
    }
}
