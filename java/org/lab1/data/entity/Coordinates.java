package org.lab1.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lab1.bean.data.Identable;

@Entity
@Table(name = "coordinates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates implements Identable, Ownerable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Primary key, auto-generated

    @Column(nullable = false)
    @Max(value = 755, message = "Value must be less than 756")
    private Float x = 0F; // Cannot be null

    @Column()
    @Max(value = 685, message = "Value must be less than 686")
    private int y;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner; // Cannot be null
}