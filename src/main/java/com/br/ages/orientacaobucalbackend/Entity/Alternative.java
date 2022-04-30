package com.br.ages.orientacaobucalbackend.Entity;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.br.ages.orientacaobucalbackend.enums.AlternativeCriticalLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "alternative")
// @JsonIdentityInfo(
//   generator = ObjectIdGenerators.PropertyGenerator.class, 
//   property = "id")
public class Alternative {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Getter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    // @JsonBackReference
    @JsonIgnore
    private Question question;

    @Getter
    @Setter
    private String alternativeText;
    
    @Getter
    @Setter
    private AlternativeCriticalLevel criticalLevel;
}
