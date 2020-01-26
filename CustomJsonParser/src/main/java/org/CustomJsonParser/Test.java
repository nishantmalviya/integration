package org.CustomJsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class Test {

	public static void main(String[] args) throws IOException {
		JsonReader jsonReader = new JsonReader(new FileReader(
				new File("C:\\Users\\Nishant\\git\\integration\\CustomJsonParser\\src\\main\\resources\\sample.json")));

		try {
			while (jsonReader.hasNext()) {
				JsonToken nextToken = jsonReader.peek();
				System.out.println(nextToken);

				if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {

					jsonReader.beginObject();

				} else if (JsonToken.NAME.equals(nextToken)) {

					String name = jsonReader.nextName();
					System.out.println(name);

				} else if (JsonToken.STRING.equals(nextToken)) {

					String value = jsonReader.nextString();
					System.out.println(value);

				} else if (JsonToken.NUMBER.equals(nextToken)) {

					long value = jsonReader.nextLong();
					System.out.println(value);

				} else if (JsonToken.BEGIN_ARRAY.equals(nextToken)) {

					jsonReader.beginArray();

					while (!JsonToken.END_ARRAY.equals(nextToken)) {

						while (jsonReader.hasNext()) {
							nextToken = jsonReader.peek();

							if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {

								jsonReader.beginObject();

							} else if (JsonToken.NAME.equals(nextToken)) {

								String name = jsonReader.nextName();
								System.out.println("**" + name);

							} else if (JsonToken.STRING.equals(nextToken)) {

								String value = jsonReader.nextString();
								System.out.println("**" + value);

							} else if (JsonToken.NUMBER.equals(nextToken)) {

								long value = jsonReader.nextLong();
								System.out.println("**" + value);

							} else if (JsonToken.BEGIN_ARRAY.equals(nextToken)) {

								jsonReader.beginArray();

							}
						}
						nextToken = jsonReader.peek();
						if (JsonToken.STRING.equals(nextToken)) {

							String value = jsonReader.nextString();
							System.out.println("**" + value);

						}
						if (JsonToken.END_OBJECT.equals(nextToken)) {

							jsonReader.endObject();

						}
					}

					if (JsonToken.END_ARRAY.equals(nextToken)) {
						jsonReader.endArray();
					}

				}

			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			jsonReader.close();
		}

	}

}
