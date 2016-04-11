package ua.org.gostroy.domain.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by Sergey on 4/7/2016.
 */
public class Feedback {

    private Long id;
    private Byte rating;
    private String feedback;
    private User user;
    private Trip trip;


    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getRating() {
        return rating;
    }

    public void setRating(Byte rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
