package org.lab1.bean.data;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.entity.enums.EventType;

import lombok.Data;
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
@Data
public class EventTypeBean {
    private List<EventType> typeList;

    public EventTypeBean() {
        typeList = List.of(EventType.values());
    }
}
