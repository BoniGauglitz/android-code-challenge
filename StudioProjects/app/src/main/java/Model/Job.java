package Model;


import java.io.Serializable;

import Util.StringUtil;

public class Job implements Serializable {
    private long id;
    private String title;
    private String alternative_name;
    private String description;
    private Address address;
    public Job() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlternative_name() {
        return alternative_name;
    }

    public void setAlternative_name(String alternative_name) {
        this.alternative_name = alternative_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddressString()
    {
        if(address!=null)
        return StringUtil.checkValue(address.getAddress(),-1,"(Sem endereço)")
                + ", " + StringUtil.checkValue(address.getCity(),-1,"(Sem cidade)");
        else return "(Sem endereço)";
    }
}
