package servlet;

/**
 * Created by dan on 13.08.2017.
 */
public class Bean {




    public Bean(int id, String value, boolean isDone) {
        this.id=id;
        this.whatToDo=value;
        this.isDone=isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWhatToDo() {
        return whatToDo;
    }

    public void setWhatToDo(String whatToDo) {
        this.whatToDo = whatToDo;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    private int id;
    private String whatToDo;
    private boolean isDone;

}
