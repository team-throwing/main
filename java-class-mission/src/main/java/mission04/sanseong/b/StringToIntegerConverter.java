package mission04.sanseong.b;

public class StringToIntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String s) {
        return Integer.parseInt(s);
    }
}
