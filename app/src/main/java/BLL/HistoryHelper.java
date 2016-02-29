package BLL;

import java.util.ArrayList;

import Models.BEHistory;

/**
 * Created by mads on 2/29/16.
 */
public class HistoryHelper {

    private static HistoryHelper instance;
    ArrayList<BEHistory> history = new ArrayList<>();

    private HistoryHelper(){};

    public static HistoryHelper getInstance(){
        if( instance == null){
            instance = new HistoryHelper();
        }
        return instance;
    }

    public void add(BEHistory hist){
        history.add(hist);
    }

    public void clearAll(){
        history.clear();
    }

    public ArrayList<BEHistory> getAll(){
        return history;
    }
}
