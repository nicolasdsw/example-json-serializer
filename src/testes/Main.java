package testes;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		JsonUtil.getFields(Usuario.class);
		JsonUtil.getFields(Papel.class);
		
		var users = IntStream.range(0, 1_000_000).boxed().map(i -> {
			Usuario user = new Usuario(null, "augusto@email.com", 2000);
			user.setId(i);
//			user.setPapel(new Papel(i * 2, "Adm"));
			return user;
		}).collect(Collectors.toList());

		var inicio = System.currentTimeMillis();
		var jsonArr = users.stream().map(user -> {
			return user.toJsonString();
		}).collect(Collectors.toList());
		var fim = System.currentTimeMillis();
		System.out.println((fim - inicio) + "ms");

		System.out.println(jsonArr.subList(0, 3).stream().collect(Collectors.joining(",\n")));
	}
}
