package TryandError;

import java.lang.reflect.Method;

public class Tester {

	public static void main(String args[]) throws Exception {
		new Tester().reflectionTest();
	}

	public void reflectionTest() throws Exception {
		Person person = new Person("John Doe", "555-123-4567", "Rover");
		// Field[] fields = person.getClass().getDeclaredFields();
		// for (Field field : fields) {
		// field.setAccessible(true); //Additional line
		// System.out.println("Field Name: " + field.getName());
		// System.out.println("Field Type: " + field.getType());
		// System.out.println("Field Value: " + field.get(person));
		// //The line above throws: Exception in thread "main"
		// java.lang.IllegalAccessException: Class main.Tester can not access a
		// member of class main.Tester$Person with modifiers "private final"
		// }
		// Introspector.getBeanInfo();
		Method[] fields = person.getClass().getMethods();
		// Testing t = new Testing("val1111", false);
		// Class tClass = t.getClass();
		// Method gs1Method = tClass.getMethod("getString1", new Class[] {});
		// String str1 = (String) gs1Method.invoke(t, new Object[] {});
		// System.out.println("getString1 returned: " + str1);

		for (Method field : fields) {
			// field.setAccessible(true); //Additional line
			System.out.println("Field Name: " + field.getName());
			// String str1 = (String) field.invoke(person, new Object[] {});
			// System.out.println("Field waarde: " + str1);

			// method.invoke(obj, arg1, arg2,...);
			// int result = (Integer) testMethod.invoke(new ReflectionExample(),
			// 100);
			// System.out.println("Field Type: " + field.getDefaultValue());
			// System.out.println("Field Value: " + field.get(person));
			// The line above throws: Exception in thread "main"
			// java.lang.IllegalAccessException: Class main.Tester can not
			// access a member of class main.Tester$Person with modifiers
			// "private final"
		}

		printGettersSetters(person.getClass());
	}

	public static void printGettersSetters(Class aClass) {
		Method[] methods = aClass.getMethods();
		// methods[0].i
		for (Method method : methods) {
			if (isGetter(method))
				System.out.println("getter: " + method);
			if (isSetter(method))
				System.out.println("setter: " + method);
		}
	}

	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("Get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}

	public static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set"))
			return false;
		if (method.getParameterTypes().length != 1)
			return false;
		return true;
	}

	public class Person {

		private String name;
		private String phoneNumber;
		private String dogsName;

		public Person(String name, String phoneNumber, String dogsName) {
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.dogsName = dogsName;
		}

		public String GetName() {
			return name;
		}

		public String GetPhoneNumber() {
			return phoneNumber;
		}

		public String GetDogsName() {
			return dogsName;
		}
	}
}