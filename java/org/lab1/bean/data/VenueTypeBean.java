package org.lab1.bean.data;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.entity.enums.VenueType;

import lombok.Data;
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
@Data
public class VenueTypeBean {
    private List<VenueType> typeList;

    public VenueTypeBean() {
        typeList = List.of(VenueType.values());
    }
}
