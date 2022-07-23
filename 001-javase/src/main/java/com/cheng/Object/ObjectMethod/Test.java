package com.cheng.Object.ObjectMethod;

public class Test {

    public static void main(String[] args) {
        Emp alice1 = new Emp("Alice Adams",75000,1987,12,15);

        Emp alice2 = alice1;

        Emp alice3 = new Emp("Alice Adams",75000,1987,12,15);

        Emp bob = new Emp("Bob BrandSon" ,50000,1989,10,1);

        System.out.println("alice1==alice2: "+(alice1==alice2));//alice1==alice2: true

        System.out.println("alice1==alice3: "+(alice1==alice3));//alice1==alice3: false

        System.out.println("alice1.equals(alice3):"+alice1.equals(alice3));//alice1.equals(alice3):true

        System.out.println("alice1.equals(bob)"+alice1.equals(bob));//false;
        System.out.println("bob.toString: "+bob);

        Man carl = new Man("Carl Cracker",80000,1987,12,15);
        Man boss = new Man("Carl Cracker",80000,1987,12,15);
        boss.setBonus(5000);
        System.out.println("boss ToString: "+boss);
        System.out.println("carl.equals(boss): "+carl.equals(boss));
        System.out.println("alice1.hashCode(): "+alice1.hashCode());// -808853550
        System.out.println("alice3.hashCode(): "+alice3.hashCode());// -808853550
        System.out.println("bob.hashCode(): "+bob.hashCode());
        System.out.println("carl.hashCode(): "+carl.hashCode());
    }


}
