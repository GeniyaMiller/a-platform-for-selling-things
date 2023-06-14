package ru.skypro.homework.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ads")
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Image> images;

    public Ads(Integer id, User author, String title, int price, String description, List<Image> images) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.description = description;
        this.images = images;
    }

    public Ads() {

    }


    public Integer getId() {
        return id;
    }

    @Id
    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return getPrice() == ads.getPrice() && Objects.equals(getId(), ads.getId()) && Objects.equals(getAuthor(), ads.getAuthor()) && Objects.equals(getTitle(), ads.getTitle()) && Objects.equals(getDescription(), ads.getDescription()) && Objects.equals(getImages(), ads.getImages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getTitle(), getPrice(), getDescription(), getImages());
    }

    @Override
    public String toString() {
        return "Ads{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", images=" + images +
                '}';
    }
}
