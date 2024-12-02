package org.lab1.bean.data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.lab1.data.entity.Address;
import org.lab1.bean.data.abstracts.UsedManagerBean;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
@ManagedBean(name = "addressBean")
@SessionScoped
@Getter
@Setter
public class AddressBean extends UsedManagerBean<Address> {
    private String itemName = "address";
    
    public AddressBean() {
        super(Address.class, "addressBean"); 
    }

    protected AddressBean(Class<Address> classType, String name) {
        super(classType, name);
    }

    @Override
    public List<Long> getIdList() {
        return getItems().stream().map(Address::getId).collect(Collectors.toList());
    }

    @Override
    public void emptyInstance() {
        super.getItemsStack().push(new Address());
        super.getStackItem().setId(0L);
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("id",
                "street",
                "zipCode"
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false; 

        AddressBean that = (AddressBean) obj;
        return Objects.equals(this.getStackItem(), that.getStackItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStackItem());
    }
}
