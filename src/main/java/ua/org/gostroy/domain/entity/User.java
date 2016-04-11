package ua.org.gostroy.domain.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by Sergey on 4/7/2016.
 */
public class User {

    private Long id;
    private String email;
    private String password;
    private List<Trip> wishList;


    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Trip> getWishList() {
        return wishList;
    }

    public void setWishList(List<Trip> wishList) {
        this.wishList = wishList;
    }
}
