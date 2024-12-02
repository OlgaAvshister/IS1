package org.lab1.bean.data;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.entity.enums.TicketType;

import lombok.Data;
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
@Data
public class TicketTypeBean {
    private List<TicketType> typeList;

    public TicketTypeBean() {
        typeList = List.of(TicketType.values());
    }
}
