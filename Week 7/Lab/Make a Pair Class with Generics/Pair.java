// Q4-W7: Make a Pair Class with Generics

public class Pair<T1, T2> {
    private T1 first;
    private T2 second;
    Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    public T1 fst() {
        return first;
    }
    public T2 snd() {
        return second;
    }
}
