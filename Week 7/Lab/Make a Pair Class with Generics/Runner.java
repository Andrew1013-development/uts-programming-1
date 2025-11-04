public class Runner {
	public static void main(String[] args) {
		Pair<Integer,String> test = new Pair<>(1,"test string");

        System.out.println(test.fst());
        System.out.println(test.snd());
	}
}
