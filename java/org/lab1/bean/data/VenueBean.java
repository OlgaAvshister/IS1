package org.lab1.bean.data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.lab1.data.Actions;
import org.lab1.data.entity.Address;
import org.lab1.data.entity.Venue;
import org.lab1.bean.data.abstracts.UsedManagerBean;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
@ManagedBean(name = "venueBean")
@SessionScoped
@Getter
@Setter
public class VenueBean extends UsedManagerBean<Venue> {
    private String itemName = "venue";
    public VenueBean() {
        super(Venue.class, "venue");
    }

    @Override
    public void addItem() {
        Venue selectedItem = super.itemsStack.peek();

        FacesContext facesContext = FacesContext.getCurrentInstance();

        assert selectedItem != null;
        if (selectedItem.getPassedAddressId() == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choose address", null));
            return;
        }
        Address selectedAddress = Actions.find(Address.class, selectedItem.getPassedAddressId());
        selectedItem.setAddress(selectedAddress);
        super.addItem();
    }

    @Override
    public void editItem() {
        Venue selectedItem = super.itemsStack.peek();

        assert selectedItem != null;
        if (!Objects.equals(selectedItem.getPassedAddressId(), selectedItem.getAddress().getId())) {
            Address selectedAddress = Actions.find(Address.class, selectedItem.getPassedAddressId());
            selectedItem.setAddress(selectedAddress);
        }
        super.editItem();
    }

    @Override
    public List<Long> getIdList() {
        return getItems().stream().map(Venue::getId).collect(Collectors.toList());
    }

    @Override
    public void emptyInstance() {
        super.getItemsStack().push(new Venue());
        super.getStackItem().setId(0L);
        super.getStackItem().setAddress(new Address());
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("id",
                "name",
                "capacity",
                "type",
                "address"
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false; 

        VenueBean that = (VenueBean) obj;
        return Objects.equals(this.getStackItem(), that.getStackItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStackItem());
    }
}
