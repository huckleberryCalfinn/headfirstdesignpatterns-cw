package com.huckleberrycalfinn.scratch;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_OFFSET_TIME;

public class ScratchApp {
	public static void main(String[] args){
//	  String t = LocalDateTime.now(UTC).atOffset(ZoneOffset.UTC).format(ISO_OFFSET_DATE_TIME);
//	  System.out.println(t);
//	  String offsetTime = LocalDateTime.now(UTC).atOffset(ZoneOffset.UTC).format(ISO_OFFSET_TIME);
//	  System.out.println(offsetTime);
//	  String localDateTime = LocalDateTime.now(UTC).format(ISO_LOCAL_DATE_TIME);
//	  System.out.println(localDateTime);
	  System.out.println(LocalDateTime.of(2022, 6, 16, 0, 0).atOffset(UTC).format(ISO_OFFSET_DATE_TIME).equals("2022" +
																													   "-06-16T00:00:00Z"));
	}
}
