package org.lab1.data.entity;

import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.lab1.data.Actions;
import org.lab1.bean.auth.UserBean;
import org.lab1.bean.data.Identable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Change implements Ownerable, Identable {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key, auto-generated

    @Column(nullable = false)
    private String entity;

    @Column(nullable = false)
    private Date changeDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner; // Cannot be null

    @Column(nullable = false)
    private String type;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User owner) {
        if (owner == null){
            Map<String, Object> session =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            this.owner = Actions.find(User.class, ((UserBean) session.get("userBean")).getId());
        } else{
            this.owner = owner;
        }
    }
}

