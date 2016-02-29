package Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mads on 2/29/16.
 */
public class BEHistory {

    private Date time;
    ArrayList<Integer> result;

    public BEHistory(Date time, ArrayList<Integer> result){
        this.time = time;
        this.result = result;
    }

    public Date getTime() {
        return time;
    }

    public ArrayList<Integer> getResult() {
        return result;
    }
}
