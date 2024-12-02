package org.lab1.bean.data;

import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.entity.Coordinates;
import org.lab1.bean.data.abstracts.UsedManagerBean;
@SuppressWarnings("deprecation")
@ManagedBean(name = "coordinatesBean")
@SessionScoped
public class CoordinateBean extends UsedManagerBean<Coordinates> {

    public CoordinateBean() {
        super(Coordinates.class, "coordinate");
    }

    @Override
    public List<Long> getIdList() {
        return getItems().stream().map(Coordinates::getId).collect(Collectors.toList());
    }

    @Override
    public void emptyInstance() {
        super.getItemsStack().push(new Coordinates());
        super.getStackItem().setId(0);
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("id", "x", "y");
    }


}
