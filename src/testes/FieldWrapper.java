package testes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldWrapper {
	private Field field;
	private boolean isNumeric;
	private boolean isStatic;
	private boolean isJsonSerializable;
	private FieldType type;

	enum FieldType {
		NUMERIC {
			public String getJsonValue(Object value, String leftSpace) {
				return String.valueOf(value);
			}
		},
		STRING {
			public String getJsonValue(Object value, String leftSpace) {
				return (value == null) ? "null" : "\"" + value.toString() + "\"";
			}
		},
		COMPOSITION {
			public String getJsonValue(Object value, String leftSpace) {
				return JsonUtil.toJsonString((JsonSerializable) value, leftSpace + "  ");
			}
		};
		public abstract String getJsonValue(Object obj, String leftSpace);
	}

	public FieldWrapper(Field field) {
		field.setAccessible(true);
		this.field = field;
		this.isStatic = Modifier.isStatic(field.getModifiers());
		this.isNumeric = isNumeric(field);
		this.isJsonSerializable = JsonSerializable.class.isAssignableFrom(field.getType());

		if (this.isNumeric) {
			type = FieldType.NUMERIC;
		} else if (this.isJsonSerializable) {
			type = FieldType.COMPOSITION;
		} else {
			type = FieldType.STRING;
		}
	}

	@Override
	public String toString() {
		return field.getName();
	}

	private static boolean isNumeric(Field field) {
		return Number.class.isAssignableFrom(field.getType()) || field.getType() == int.class
				|| field.getType() == long.class;
	}

	public String getName() {
		return this.field.getName();
	}

	public Object get(Object obj) throws IllegalArgumentException, IllegalAccessException {
		return this.field.get(obj);
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public boolean isNumeric() {
		return isNumeric;
	}

	public void setNumeric(boolean isNumeric) {
		this.isNumeric = isNumeric;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isJsonSerializable() {
		return isJsonSerializable;
	}

	public void setJsonSerializable(boolean isJsonSerializable) {
		this.isJsonSerializable = isJsonSerializable;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}
}
