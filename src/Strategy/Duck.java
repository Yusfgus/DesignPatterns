package Strategy;
import java.util.Scanner;

//====================================== STRATEGY PATTERN =======================================================

interface FlyBehavior {
    public void fly();
}
interface QuackBehavior {
    public void quack();
}

abstract class Duck {
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void changeQuackBehavior() {
        System.out.println("\nChoose:\n1-Quack 2-Squeak 3-Mute");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                quackBehavior = new Quack();
                break;
            case 2:
                quackBehavior = new Squeak();
                break;
            case 3:
                quackBehavior = new MuteQuack();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void swim(){
        System.out.println("I'm swimming");
    }
    public abstract void display();
}

class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        // implements duck flying
        System.out.println("I can fly");
    }
}

class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        // do nothing - can't fly
        System.out.println("I can't fly");
    }
}

class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket!");
    }
}

class Quack implements QuackBehavior {
    @Override
    public void quack() {
        // implements duck quacking
        System.out.println("I can quack");
    }
}

class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        // implements duck squeak
        System.out.println("I can squeak");
    }
}

class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        // do nothing - can't quack
        System.out.println("I can't quack");
    }
}

//=============================================================================================================

class MallardDuck extends Duck {
    public MallardDuck() {
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}

class RedheadDuck extends Duck {
    public RedheadDuck() {
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("I'm a redhead duck");
    }
}

class ModelDuck extends Duck {
    public ModelDuck() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }
}

class DuckCallDevice {
    public QuackBehavior quackBehavior;

    DuckCallDevice(QuackBehavior quackBehavior){
        this.quackBehavior = quackBehavior;
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void setQuack(QuackBehavior quackBehavior){
        this.quackBehavior = quackBehavior;
    }
}