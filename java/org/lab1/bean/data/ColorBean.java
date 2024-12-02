package org.lab1.bean.data;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.entity.enums.Color;

import lombok.Data;
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
@Data
public class ColorBean {
    private final List<Color> typeList;

    public ColorBean() {
        typeList = List.of(Color.values());
    }
}
