package generalized_dynamic_array;

public class Runner {
    public static void main(String[] args) {

        User user1 = new User(1);
        User user2 = new User(2);
        User user3 = new User(3);
        User user4 = new User(4);
        User user5 = new User(5);
        User user6 = new User(6);

        GeneralizedArray<User> generalizedArray = new GeneralizedArray<>();

        generalizedArray.add(user1);
        generalizedArray.add(user2);
        generalizedArray.add(user3);
        generalizedArray.add(user4);
        generalizedArray.add(user5);
        generalizedArray.add(user6);

        generalizedArray.get(2);
        generalizedArray.remove(2);
    }
}
