package org.lab1.bean.data;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.lab1.data.Actions;
import org.lab1.data.entity.Coordinates;
import org.lab1.data.entity.Event;
import org.lab1.data.entity.Person;
import org.lab1.data.entity.Ticket;
import org.lab1.data.entity.Venue;
import org.lab1.bean.data.abstracts.ManagerBean;

import lombok.Getter;
import lombok.Setter;
@SuppressWarnings("deprecation")
@ManagedBean(name = "ticketBean")
@SessionScoped
@Getter
@Setter
public class TicketBean extends ManagerBean<Ticket> {

    public TicketBean() {
        super(Ticket.class, "ticket");
    }

    @Override
    public void addItem() {
        Ticket selectedItem = super.itemsStack.peek();

        FacesContext facesContext = FacesContext.getCurrentInstance();

        assert selectedItem != null;
        if (selectedItem.getPassedPersonId() == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choose person", null));
            return;
        }
        if (selectedItem.getPassedEventId() == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choose event", null));
            return;
        }
        if (selectedItem.getPassedCoordinatesId() == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choose coordinates", null));
            return;
        }
        if (selectedItem.getPassedVenueId() == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choose venue", null));
            return;
        }

        Person selectedPerson = Actions.find(Person.class, selectedItem.getPassedPersonId());
        selectedItem.setPerson(selectedPerson);

        Event selectedEvent = Actions.find(Event.class, selectedItem.getPassedEventId());
        selectedItem.setEvent(selectedEvent);

        Coordinates selectedCoord = Actions.find(Coordinates.class, selectedItem.getPassedCoordinatesId());
        selectedItem.setCoordinates(selectedCoord);

        Venue selectedVenue = Actions.find(Venue.class, selectedItem.getPassedVenueId());
        selectedItem.setVenue(selectedVenue);

        super.addItem();
    }

    @Override
    public void editItem() {
        Ticket selectedItem = super.itemsStack.peek();

        assert selectedItem != null;
        if (!Objects.equals(selectedItem.getPassedPersonId(), selectedItem.getPerson().getId())) {
            Person selectedPerson = Actions.find(Person.class, selectedItem.getPassedPersonId());
            selectedItem.setPerson(selectedPerson);
        }

        if (!Objects.equals(selectedItem.getPassedEventId(), selectedItem.getEvent().getId())) {
            Event selectedEvent = Actions.find(Event.class, selectedItem.getPassedEventId());
            selectedItem.setEvent(selectedEvent);
        }

        if (selectedItem.getPassedCoordinatesId() != selectedItem.getCoordinates().getId()) {
            Coordinates selectedCoord = Actions.find(Coordinates.class, selectedItem.getPassedCoordinatesId());
            selectedItem.setCoordinates(selectedCoord);
        }

        if (selectedItem.getPassedVenueId() != selectedItem.getVenue().getId()) {
            Venue selectedVenue = Actions.find(Venue.class, selectedItem.getPassedVenueId());
            selectedItem.setVenue(selectedVenue);
        }

        super.editItem();
    }


    @Override
    public List<Long> getIdList() {
        return getItems().stream().map(Ticket::getId).collect(Collectors.toList());
    }

    @Override
    public void emptyInstance() {
        super.getItemsStack().push(new Ticket());
        super.getStackItem().setId(0);
        super.getStackItem().setCreationDate(new Date());
        super.getStackItem().setPerson(new Person());
        super.getStackItem().setCoordinates(
                new Coordinates()
        );

        super.getStackItem().setVenue(
                new Venue()
        );

        super.getStackItem().setEvent(
                new Event()
        );
    }


    @Override
    public List<String> getFieldNames() {
        return List.of(
                "id",
                "name",
                "coordinates",
                "creationDate",
                "person",
                "event",
                "price",
                "type",
                "discount",
                "number",
                "venue"
        );
    }
}
