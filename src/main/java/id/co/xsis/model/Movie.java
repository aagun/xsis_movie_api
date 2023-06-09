package id.co.xsis.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Movie extends BaseModel {

    @Id
    @NotNull
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private String title;

    @Size(max = 1000)
    private String description;

    private Float rating = 0F;

    private Integer duration;

    private String image;

    private String trailerLink;

}
