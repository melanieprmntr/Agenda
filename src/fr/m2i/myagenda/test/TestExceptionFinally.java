package fr.m2i.myagenda.test;

public class TestExceptionFinally {

	public static void main(String[] args) {

		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("Ceci est l'entrée dans le catch");
				if (i > 5) {
					throw new Exception("$$$$$ Ceci est une exception");
				}
				System.out.println(">>>> Ce code se déclenche que si i < 5");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			System.out.println("#### Ce code doit toujours être appelé");
		}

	}

}
