package org.linkedin.application.post;

import java.util.*;

public class Poll extends Post {
    String object1;
    String object2;
    public Poll(String object1, String object2){
        this.object1 = object1;
        this.object2 = object2;
    }

    public String getDetails(){
        Scanner scanner = new Scanner(System.in);
        int countOfObject1 = 0, countOfObject2 = 0;
        System.out.println("a polling has been started between " +
                object1 +" " +" and "+" " + object2);
        System.out.println(countOfObject1 + " " + " preferred " + " " + object1);
        System.out.println(countOfObject2 + " " + " preferred " + " " + object2);

        System.out.println("Which do you prefer");
        System.out.println(object1 + " " + object2);
        if(scanner.nextLine().equals(object1)) countOfObject1++;
        else if(scanner.nextLine().equals(object2)) countOfObject2++;

        return countOfObject1 + " : " + countOfObject2;
    }
}
