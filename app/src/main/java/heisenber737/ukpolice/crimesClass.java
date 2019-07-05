package heisenber737.ukpolice;

public class crimesClass {
    String category,month,location,outcome;
    public crimesClass(String category, String location, String month,String outcome)
    {
        this.category=category;
        this.location=location;
        this.month=month;
        this.outcome=outcome;
    }

    public String getCategory() {
        return category;
    }

    public String getMonth() {
        return month;
    }

    public String getLocation() {
        return location;
    }
    public String getOutcome() {return outcome;}


}
