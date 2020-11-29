package com.github.zxh.classpy.gui.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

// A very simple EventBus implementation.
public class EventBus {

    private final Map<Class<?>, List<Consumer<?>>> listeners = new HashMap<>();

    public <T> void sub(Class<T> cls, Consumer<T> listener) {
        listeners.computeIfAbsent(cls, k -> new ArrayList<>())
            .add(listener);
    }

    public void pub(Object event) {
        if (listeners.containsKey(event.getClass())) {
            listeners.get(event.getClass())
                    .forEach(listener -> notifyListener(listener, event));
        }
    }

    @SuppressWarnings({"unchecked", "raw"})
    private static void notifyListener(Consumer<?> x, Object event) {
        ((Consumer<Object>) x).accept(event);
    }

//    public static void main(String[] args) {
//        var eb = new EventBus();
//        eb.sub(String.class, x -> System.out.println("str:" + x));
//        eb.sub(Integer.class, x -> System.out.println("int:" + x));
//        eb.pub("123");
//        eb.pub(123);
//    }

}
