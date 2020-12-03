package com.github.zxh.classpy.gui.events;

import java.util.*;
import java.util.function.Consumer;

// A very simple EventBus implementation.
public class EventBus {

    private final Map<Class<?>, List<Consumer<?>>> listeners = new HashMap<>();

    public <T> void sub(Class<T> cls, Consumer<T> listener) {
        listeners.computeIfAbsent(cls, k -> new ArrayList<>())
            .add(listener);
    }

    @SuppressWarnings("unchecked")
    public <T> void pub(T event) {
        listeners.getOrDefault(event.getClass(), Collections.emptyList())
                .forEach(listener -> ((Consumer<T>) listener).accept(event));
    }

//    public static void main(String[] args) {
//        var eb = new EventBus();
//        eb.sub(String.class, x -> System.out.println("str:" + x));
//        eb.sub(Integer.class, x -> System.out.println("int:" + x));
//        eb.pub("123");
//        eb.pub(123);
//    }

}
