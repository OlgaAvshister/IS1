package org.lab1.bean.data.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuBean {
    static String prefix = "tables/";
    static String postfix = "/main.xhtml";

    String contentName = "coordinate";

    public String goCoordinates() {
        return "coordinate";
    }

    public String goAddress() {
        return "address";
    }

    public String goEvent() {
        return "event";
    }

    public String goLocation() {
        return "location";
    }

    public String goPerson() {
        return "person";
    }

    public String goTicket() {
        return "ticket";
    }

    public String goVenue() {
        return "venue";
    }

    public String goUsers() {
        return "users";
    }

    public String goExtra() {
        return "extra";
    }

    public String goChanges() {
        return "changes";
    }

    public String setContentName(String contentName) {
        this.contentName = contentName;
        return contentName;
    }

    public String getContentName() {
        return prefix + contentName + postfix;
    }


}
