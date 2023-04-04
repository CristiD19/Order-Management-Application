package start;

import java.lang.reflect.Field;

public class ReflectionExample {

	public static String retrieveProperties(Object object) {

		StringBuilder sb = new StringBuilder();
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); // set modifier to public
			Object value;
			try {
				value = field.get(object);
				System.out.println(field.getName() + "=" + value);
				sb.append(value);
				sb.append(System.getProperty("line.separator"));

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		return sb.toString();
	}
}
