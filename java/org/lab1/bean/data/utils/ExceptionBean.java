package org.lab1.bean.data.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Data;
@SuppressWarnings("deprecation")
@ManagedBean(name = "exceptionBean")
@SessionScoped
@Data
public class ExceptionBean {
    int errorCode;
}
