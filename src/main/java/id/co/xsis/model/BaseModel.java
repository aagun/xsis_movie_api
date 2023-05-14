package id.co.xsis.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class BaseModel {

    @NotNull
    @NotEmpty
    @CreatedBy
    private String createdBy;

    @NotNull
    @CreatedDate
    private LocalDateTime createdAt;

    @NotNull
    @NotEmpty
    @LastModifiedBy
    private String updatedBy;

    @NotNull
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
