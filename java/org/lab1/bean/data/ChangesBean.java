package org.lab1.bean.data;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.lab1.data.Actions;
import org.lab1.data.entity.Change;

import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("deprecation")
@ManagedBean(name = "changesBean")
@ViewScoped
@Data
@NoArgsConstructor
public class ChangesBean {
    public List<Change> getChangesList(){
        List<Change> listChange = Actions.findAll(Change.class);
        System.out.println(listChange);
        return listChange;
    }
}
