package org.lab1.bean.data;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.entity.Event;
import org.lab1.bean.data.abstracts.UsedManagerBean;
@SuppressWarnings("deprecation")
@ManagedBean(name = "eventBean")
@SessionScoped
public class EventBean extends UsedManagerBean<Event> {
    private static final Logger logger = Logger.getLogger(EventBean.class.getName());
    public EventBean() {
        super(Event.class, "event");
    }
    @Override
    public List<Long> getIdList() {
        return getItems().stream().map(Event::getId).collect(Collectors.toList());

    }

    @Override
    public void emptyInstance() {
        super.getItemsStack().push(new Event());
        super.getStackItem().setId(0L);
    }

    @Override
    public void addItem() {
        super.addItem();
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("id", "name", "date", "minAge", "ticketsCount", "eventType");
    }

}
