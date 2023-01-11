package testes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonUtil {

	public static Map<String, List<FieldWrapper>> fieldsMap = new HashMap<>();

	public static List<FieldWrapper> getFields(Class<?> clazz) {
		if (fieldsMap.containsKey(clazz.getName())) {
			return fieldsMap.get(clazz.getName());
		} else {
			var fields = Stream.of(clazz.getDeclaredFields()).map(FieldWrapper::new).filter(f -> !f.isStatic())
					.collect(Collectors.toList());
			fieldsMap.put(clazz.getName(), fields);
			return fields;
		}
	}

	/**
	 * UTILIZAR APENAS EM SOLUÇÕES ESPECIFICAS
	 * @param obj
	 * @return
	 */
	public static String toJsonString(JsonSerializable obj) {
		return toJsonString(obj, "");
	}

	public static String toJsonString(JsonSerializable obj, String leftSpace) {
		if (obj == null) {
			return "null";
		}
		var fields = getFields(obj.getClass());
		var sb = new StringBuilder();
		sb.append("{");
//		sb.append("{\n");
		for (var field : fields) {
//			sb.append(leftSpace);
//			sb.append("  ");
			try {
				sb.append("\"");
				sb.append(field.getName());
				sb.append("\":");
				var value = field.get(obj);
				sb.append(field.getType().getJsonValue(value, leftSpace));
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			sb.append(",");
//			sb.append(", \n");
		}
		sb.setLength(sb.length() - 1);
//		sb.setLength(sb.length() - 3);
//		sb.append("\n");
//		sb.append(leftSpace);
		sb.append("}");
//		sb.append("\n");
		return sb.toString();
	}
}
