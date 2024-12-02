package org.lab1.bean.auth;

import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.lab1.data.Actions;
import org.lab1.data.entity.User;


@SuppressWarnings("deprecation")
@ManagedBean
@ApplicationScoped
public class UserManagerBean {
    public List<User> getRequestList(){
        return Actions.findAll(User.class).stream().filter(User::isNotMain).collect(Collectors.toList());
    }

    public void deniedRequest(User user){
        Actions.delete(user);
    }

    public void acceptRequest(User user){
        user.setNotMain(false);
        Actions.update(user);
    }

}
