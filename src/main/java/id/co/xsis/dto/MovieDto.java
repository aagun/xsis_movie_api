package id.co.xsis.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MovieDto {

    @NotNull
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    @Size(max = 1000)
    private String description;

    private Float rating;

    private Integer duration;

    private String image;

    private String trailerLink;

}
