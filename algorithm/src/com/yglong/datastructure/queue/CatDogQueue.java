package com.yglong.datastructure.queue;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 猫狗队列
 */
public class CatDogQueue {

    private Queue<CounteredPet> catQueue;
    private Queue<CounteredPet> dogQueue;
    private long count;

    public CatDogQueue() {
        this.catQueue = new LinkedList<>();
        this.dogQueue = new LinkedList<>();
        this.count = 0;
    }

    public void add(Pet pet) {
        if (pet.getType().equals(Pet.DOG)) {
            this.dogQueue.add(new CounteredPet(pet, count++));
        } else if (pet.getType().equals(Pet.CAT)) {
            this.catQueue.add(new CounteredPet(pet, count++));
        } else {
            throw new RuntimeException("unknown pet type: " + pet.getType());
        }
    }

    public Pet pollAll() {
        if (!this.dogQueue.isEmpty() && !this.catQueue.isEmpty()) {
            long dogCounter = this.dogQueue.peek().getCount();
            long catCounter = this.catQueue.peek().getCount();
            if (dogCounter < catCounter) {
                return Objects.requireNonNull(dogQueue.poll()).getPet();
            } else {
                return Objects.requireNonNull(catQueue.poll()).getPet();
            }
        } else if (!this.dogQueue.isEmpty()) {
            return Objects.requireNonNull(dogQueue.poll()).getPet();
        } else if (!this.catQueue.isEmpty()) {
            return Objects.requireNonNull(catQueue.poll()).getPet();
        } else {
            throw new RuntimeException("Queue is empty");
        }
    }

    public Dog pollDog() {
        if (!this.dogQueue.isEmpty()) {
            return (Dog) Objects.requireNonNull(dogQueue.poll()).getPet();
        } else {
            throw new RuntimeException("Dog queue is empty");
        }
    }

    public Cat pollCat() {
        if (!this.catQueue.isEmpty()) {
            return (Cat) Objects.requireNonNull(catQueue.poll()).getPet();
        } else {
            throw new RuntimeException("Cat queue is empty");
        }
    }

    public boolean isEmpty() {
        return dogQueue.isEmpty() && catQueue.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogQueue.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQueue.isEmpty();
    }

    public static void main(String[] args) {
        CatDogQueue queue = new CatDogQueue();
        queue.add(new Cat());
        queue.add(new Dog());
        queue.add(new Dog());
        queue.add(new Cat());

        System.out.println(queue.isEmpty());
        System.out.println(queue.isCatEmpty());
        System.out.println(queue.isDogEmpty());
        System.out.println(queue.pollAll());
        System.out.println(queue.pollCat());
        System.out.println(queue.pollDog());
    }
}

class CounteredPet {
    private Pet pet;
    private long count;

    public CounteredPet(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}

class Pet {
    public static final String CAT = "cat";
    public static final String DOG = "dog";
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class Cat extends Pet {
    public Cat() {
        super(CAT);
    }
}

class Dog extends Pet {
    public Dog() {
        super(DOG);
    }
}
