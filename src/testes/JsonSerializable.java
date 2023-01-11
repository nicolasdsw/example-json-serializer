package testes;

public interface JsonSerializable {
	
	default String toJsonString() {
		return JsonUtil.toJsonString(this);
	}
}
