package org.lab1.bean.data.abstracts;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.lab1.context.MyException;
import org.lab1.data.Actions;
import org.lab1.data.entity.Ownerable;
import org.lab1.data.entity.Ticket;
import org.lab1.bean.data.Identable;
import org.lab1.bean.data.TicketBean;

public abstract class UsedManagerBean<T extends Ownerable & Identable> extends ManagerBean<T> {
    private static final Logger logger = Logger.getLogger(UsedManagerBean.class.getName());

    protected UsedManagerBean(Class<T> classType, String name) {
        super(classType, name);
    }

    @Override
    public void removeItem() {
        T stackItem = super.itemsStack.pop();

        try {
            Actions.delete(stackItem);
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            T searchedItem = Actions.find(super.classType, stackItem.getId());
            if (searchedItem == null) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item was removed", null));
            } else {
                List<Ticket> editList = Actions.findTicketByClassId(classType, stackItem.getId());

                if (!editList.isEmpty()) {
                    TicketBean viewScopedBean = getTicketBean();
                    viewScopedBean.getItemsStack().addAll(editList);
                    finishEditItem();
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                    try {
                        externalContext.redirect(externalContext.getRequestContextPath() + "/views/data/pages/ticket/main.xhtml");
                    } catch (IOException ex) {
                        throw new MyException("IOException", ex);
                    }
                }
            }
        }
    }

    public static TicketBean getTicketBean() {
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return (TicketBean) session.get("ticketBean");
    }

    public void finishEditItem() {
        TicketBean ticketBean = getTicketBean();
        ticketBean.editStack();
    }
}
