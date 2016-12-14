package br.com.adeptsd.product;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
/**
	User u = new User();
	u.setName("MARCOPS");
	List<String> categories = new ArrayList<String>();
	categories.add("CAT");
	categories.add("DOG");
	u.setCategories(categories);
	String test = u.toString();
	System.out.println(test);
	User ua = new User();
	ua = ua.fromString(test);
 * */
public class SerializableBase64<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("hiding")
	public <T> T fromString(String s, Class<T> clazz) throws IOException, ClassNotFoundException {
		byte[] data = Base64.getDecoder().decode(s);
		ObjectInputStream ois;
		ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return clazz.cast(o);
	}

	/** Write the object to a Base64 string. */
	public String toString() {
		Serializable o = (Serializable) this;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}
}
