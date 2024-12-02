package org.lab1.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

import org.lab1.data.entity.enums.TicketType;
import org.lab1.bean.data.Identable;

import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Data
@SqlResultSetMapping(
        name = "ticketsMapping",
        entities = @EntityResult(
                entityClass = Ticket.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "name", column = "name"),
                        @FieldResult(name = "coordinates", column = "coordinates_id"),
                        @FieldResult(name = "creationDate", column = "creation_date"),
                        @FieldResult(name = "person", column = "person_id"),
                        @FieldResult(name = "event", column = "event_id"),
                        @FieldResult(name = "price", column = "price"),
                        @FieldResult(name = "type", column = "type"),
                        @FieldResult(name = "discount", column = "discount"),
                        @FieldResult(name = "number", column = "number"),
                        @FieldResult(name = "venue", column = "venue_id"),
                        @FieldResult(name = "owner", column = "user_id")
                }
        )
)
public class Ticket implements Identable, Ownerable {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Must be greater than 0, unique, and generated automatically

    @Column(nullable = false)
    @NotEmpty(message = "Name cannot be empty")
    private String name; // Cannot be null, string cannot be empty

    @ManyToOne
    @JoinColumn(name = "coordinates_id", nullable = false)
    private Coordinates coordinates; // Cannot be null

    @Transient
    private Long coordinatesId;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate; // Cannot be null, generated automatically

    @Column(nullable = false)
    private double price; // Must be greater than 0

    @Column(nullable = false)
    @Max(value = 100)
    private int discount; // Must be greater than 0 and less than 100

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TicketType type; // Cannot be null

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue; // Cannot be null

    @Transient
    private Long venueId;

    @Column(name = "number")
    private Integer number = 1; // Must be greater than 0

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event; // Cannot be null

    @Transient
    private Long eventId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Transient
    private Long personId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner; // Cannot be null


    public Ticket() {
        this.creationDate = new Date(); // Automatically set creation date
    }


    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }


    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new IllegalArgumentException("Coordinates cannot be null");
        this.coordinates = coordinates;
    }


    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Price must be greater than 0");
        this.price = price;
    }


    public void setTicketType(TicketType type) {
        if (type == null) throw new IllegalArgumentException("Ticket type cannot be null");
        this.type = type;
    }


    public void setDiscount(int discount) {
        if (discount < 0 || discount > 100)
            throw new IllegalArgumentException("Discount must be greater than 0 and less than 101");
        this.discount = discount;
    }

    public void setNumber(Integer number) {
        if (number == null || number <= 0)
            throw new IllegalArgumentException("Number must be greater than 0 and cannot be null");
        this.number = number;
    }

    public void setVenue(Venue venue) {
        if (venue == null) throw new IllegalArgumentException("Venue cannot be null");
        this.venue = venue;
    }

    public void setEvent(Event event) {
        if (event == null) throw new IllegalArgumentException("Event cannot be null");
        this.event = event;
    }

    public Long getPassedEventId() {
        return eventId;
    }

    public Long getPassedPersonId(){
        return personId;
    }

    public Long getPassedCoordinatesId() {
        return coordinatesId;
    }

    public Long getPassedVenueId() {
        return venueId;
    }

    @Override
    public long getId() {
        return id.longValue();
    }
}