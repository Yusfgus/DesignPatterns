package Strategy;

public class main {
    public static void run(){
        System.out.println("Hello from Strategy Pattern!");

        Duck myDuck = new MallardDuck();
        while (true)
        {
            myDuck.performQuack();
            myDuck.changeQuackBehavior();
        }
    }
}
