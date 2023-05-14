package id.co.xsis.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Movie extends BaseModel {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(name = "title")
    @Size(min = 1, max = 100)
    private String title;

    @Column(name = "description")
    @Size(max = 1000)
    private String description;

    @Column(name = "rating")
    private Float rating = 0F;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "image")
    private String image;

    @Column(name = "trailer_link")
    private String trailerLink;

}
