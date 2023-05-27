package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateAds {
    private String description;
    private int price;
    private String title;

    public CreateAds(String description, int price, String title) {
        this.description = description;
        this.price = price;
        this.title = title;
    }

}
