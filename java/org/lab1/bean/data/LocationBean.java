package org.lab1.bean.data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.entity.Location;
import org.lab1.bean.data.abstracts.UsedManagerBean;

import lombok.Getter;
import lombok.Setter;
@SuppressWarnings("deprecation")
@ManagedBean(name = "locationBean")
@SessionScoped
@Getter
@Setter
public class LocationBean extends UsedManagerBean<Location> {
    private String itemName = "location";

    public LocationBean() {
        super(Location.class, "location");
    }


    @Override
    public List<Long> getIdList() {
        return getItems().stream().map(Location::getId).collect(Collectors.toList());
    }

    @Override
    public void emptyInstance() {
        super.getItemsStack().push(new Location());
        super.getStackItem().setId(0L);
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("id",
                "name",
                "x",
                "y"
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false; 

        LocationBean that = (LocationBean) obj;
        return Objects.equals(this.getStackItem(), that.getStackItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStackItem());
    }
}
