package custom_collector;

public class A {

    private final String TEXT;
    private final char[] TEXT_ELEMENTS;


    public A(String text) {
        this.TEXT = editText(text);
        this.TEXT_ELEMENTS = TEXT.toUpperCase().toCharArray();
    }

    private String editText(String text) {
        return text.replaceAll(" {2,}", " ").trim();
    }

    public char[] getTEXT_ELEMENTS() {
        return TEXT_ELEMENTS;
    }

    @Override
    public String toString() {
        return TEXT;
    }
}
