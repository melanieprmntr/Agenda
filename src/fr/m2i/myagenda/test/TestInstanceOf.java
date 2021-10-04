package fr.m2i.myagenda.test;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.dto.MyAgendaObject;

public class TestInstanceOf {

	public static void main(String[] args) {

		String nom = "nom";
		test(nom);

		ContactDto contact = new ContactDto();
		EventDto event = new EventDto();

		info(contact);
		info(event);
	}

	public static void info(MyAgendaObject mao) {
		System.out.println(mao.getInformation());
	}

	public static void test(Object o) {
		if (o instanceof TestInstanceOf) {
			System.out.println("C'est un TestInstanceOf");

		} else if (o instanceof String) {
			System.out.println("C'est une string");

		} else {
			System.out.println("Ce n'est pas une string ni un TestInstanceOf");
		}
	}

	public TestInstanceOf() {

	}
}
