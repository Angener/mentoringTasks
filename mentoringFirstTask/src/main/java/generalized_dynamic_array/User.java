package generalized_dynamic_array;

public class User {
    private int id;

    public User(int id){
        this.id = id;
    }

    @Override
    public String toString(){
        return "User id: " + id;
    }
}
