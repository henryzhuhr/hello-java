import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Main {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		List<String> roles = Arrays.asList("鲁班七号", "伽罗", "云中君");
		Stream<String> stream = roles.stream();
		System.out.println(stream);
		// Arrays.stream() 创建流
		String[] roles2 = { "鲁班七号", "伽罗", "云中君" };
		Stream<String> stream2 = Arrays.stream(roles2);
		// Stream.of() 创建流
		Stream<String> stream3 = Stream.of("鲁班七号", "伽罗", "云中君");
	}
}