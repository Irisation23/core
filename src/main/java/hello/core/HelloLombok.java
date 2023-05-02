package hello.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class HelloLombok {
    private final String name;
    private final int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok("hello", 15);

    }
}
