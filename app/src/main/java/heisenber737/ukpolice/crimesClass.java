package heisenber737.ukpolice;

public class crimesClass {
    String category,location,outcome,month;
    public crimesClass(String category,String month, String location,String outcome)
    {
        this.category=category;
        this.location=location;
        this.outcome=outcome;
        this.month=month;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }
    public String getOutcome() {return outcome;}

    public String getMonth()
    {
        return month;
    }


}
