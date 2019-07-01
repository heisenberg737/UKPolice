package heisenber737.ukpolice;

public class forces_cl {
    String ID,Name;
    public forces_cl(String ID,String Name)
    {
        this.setID(ID);
        this.setName(Name);
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }
}
