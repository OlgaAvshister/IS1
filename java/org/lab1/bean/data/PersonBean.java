package org.lab1.bean.data;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.lab1.data.Actions;
import org.lab1.data.entity.Location;
import org.lab1.data.entity.Person;
import org.lab1.bean.data.abstracts.UsedManagerBean;

import lombok.Getter;
import lombok.Setter;
@SuppressWarnings("deprecation")
@ManagedBean(name = "personBean")
@SessionScoped
@Getter
@Setter
public class PersonBean extends UsedManagerBean<Person> {
    private String itemName = "person";

    public PersonBean() {
        super(Person.class, "person");
    }

    @Override
    public List<Long> getIdList() {
        return getItems().stream().map(Person::getId).collect(Collectors.toList());
    }

    @Override
    public void emptyInstance() {
        super.getItemsStack().push(new Person());
        super.getStackItem().setId(0L);
        super.getStackItem().setLocation(new Location());
    }

    @Override
    public void addItem() {
        Person selectedItem = super.itemsStack.peek();

        FacesContext facesContext = FacesContext.getCurrentInstance();

        assert selectedItem != null;
        if (selectedItem.getPassedLocationId() == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choose location", null));
            return;
        }
        Location selectedLocation = Actions.find(Location.class, selectedItem.getPassedLocationId());
        selectedItem.setLocation(selectedLocation);
        super.addItem();
    }

    @Override
    public void editItem() {
        Person selectedItem = super.itemsStack.peek();

        assert selectedItem != null;
        if (!Objects.equals(selectedItem.getPassedLocationId(), selectedItem.getLocation().getId())) {
            Location selectedLocation = Actions.find(Location.class, selectedItem.getPassedLocationId());
            selectedItem.setLocation(selectedLocation);
        }
        super.editItem();
    }


    @Override
    public List<String> getFieldNames() {
        return List.of("id",
                "eyeColor",
                "hairColor",
                "location",
                "height"
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false; 

        PersonBean that = (PersonBean) obj;
        return Objects.equals(this.getStackItem(), that.getStackItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStackItem());
    }
}
