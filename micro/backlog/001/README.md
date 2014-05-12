
### Contest 001

A sha-256 is a [one way function](http://en.wikipedia.org/wiki/One-way_function) that may be stored into a database instead of the actual password of the user. If a malicious user obtains the database information, he/she still will need to use the hash function with every possible password and compare it to the stored hash to know the real one. 

However, if a user has set a weak password, every possible password can be reduced to a dictionary and thus reduce the amount of needed calculations. We've found in the Internet a java code that tries to find a password with the [cain dictionary](https://wiki.skullsecurity.org/Passwords).

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;


public class PasswordFinder {
	
	private static final String HASH_ALGORITHM = "SHA-256";
	private static final String ENCODING = "UTF-8";
	
	private final MessageDigest messageDigest;

	public PasswordFinder() throws NoSuchAlgorithmException {
		messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
	}
	
	private String calculateHash(final String text) 
			throws UnsupportedEncodingException {
		byte[] binaryHash = messageDigest.digest(text.getBytes(ENCODING));
		return DatatypeConverter.printBase64Binary(binaryHash);
	}
	
	public String findHashInDictionary(final InputStream dictionaryStream, 
			final String targetHash) throws IOException {
		BufferedReader dictionaryReader = null;
		try {
			dictionaryReader = new BufferedReader(
					new InputStreamReader(dictionaryStream, ENCODING));
			String password = null;
			while((password = dictionaryReader.readLine()) != null) {
				String hash = calculateHash(password);
				if(hash.compareTo(targetHash) == 0){
					return password;
				}
			}
				
		} finally {
			dictionaryReader.close();
		}
		
		return null;
	}
	
	public static void main(String[] args) 
			throws NoSuchAlgorithmException, IOException {
		String dictionaryPath = "/cain.txt";
		String targetHash = "tMWuKulh/ojRKCG9+UWxVILvAhcD4fkAzL4aJ/It8H8=";
		PasswordFinder passwordFinder = new PasswordFinder();
		String password = passwordFinder.findHashInDictionary(
				PasswordFinder.class.getResourceAsStream(dictionaryPath), 
				targetHash);
		if(password != null) {
			System.out.println(
					String.format("Found password in dictionary: %s", password));
		} else {
			System.out.println("Woops! The password was not there ;(");
		}
	}

}
```

To avoid this risk, we want to run the dictionary attack with all the passwords stored in the database and send an alert to the users that have a weak one. As this can take quite long, we've agreed to rewrite the code to be more efficient. In scala, obviously.

Best code gets a free pint in the next scala beers :D

