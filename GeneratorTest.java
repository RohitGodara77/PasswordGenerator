package passGenPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GeneratorTest {
	
	private final PasswordStrength password= new PasswordStrength("Secret");
	private final Characters firstAlphabet = new Characters(true,false,false,false);
	private final Characters secondAlphabet = new Characters(false,true,true,true);
	private final Generator generator = new Generator(true,false,false,false);
//	private final Password generatedPassword = generator.GeneratePassword(4);
	
	@Test
	void test1() {
		assertEquals("Secret", password.toString());
	}

	@Test
	void test2() {
		assertEquals(firstAlphabet.getCharacters(), Characters.UPPERCASE_LETTERS);
	}

	@Test
	void test3() {
		assertEquals(secondAlphabet.getCharacters(), Characters.LOWERCASE_LETTERS + Characters.NUMBERS + Characters.SYMBOLS);
	}
	
	@Test
	void test4() {
		assertEquals(generator.alphabet.getCharacters(), Characters.UPPERCASE_LETTERS);
	}
	
	@Test
	void test5() {
		assertEquals(generator.alphabet.getCharacters().length(), 26);
	}

}