package GUI;

public class ComboModel {

    private String name;
    private Object object;

    public ComboModel(String name, Object object) {
        this.name = name;
        this.object = object;
    }

    @Override
    public String toString() {
        return name;
    }

    public Object getObject(){
        return object;
    }
}
