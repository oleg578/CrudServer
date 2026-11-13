package be.hyf.srv;

public class SpringApplication {
    public static <T> T create(Class<T> source) {
        try {
            return source.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Cannot instantiate " + source.getName()
                    + ": a public no-arg constructor is required", e);
        }
    }
}
