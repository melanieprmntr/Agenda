package fr.m2i.myagenda.test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TestTruncated {

	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();

		LocalDateTime result = now.truncatedTo(ChronoUnit.HOURS);
		System.out.println("timestampToCompare.truncatedTo(ChronoUnit.HOURS: " + result);
	}
}
