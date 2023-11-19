package objects;

import java.sql.Date;

public class Match {

    private String id;
    private Date date;
    private String result;
    private String stadium;

    public Match(String id, Date date, String result, String stadium) {
        this.id = id;
        this.date = date;
        this.result = result;
        this.stadium = stadium;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

}
