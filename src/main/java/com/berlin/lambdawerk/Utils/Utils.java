package com.berlin.lambdawerk.Utils;


/**
 * Created by Chaklader on 2/23/17.
 */
public class Utils {
	
	private static final int SEC_MS = 1000;

	private static final int MIN_MS = 60000;

	private static final int HOURS_MS = 3600000;
	
	public static String getDurationStr(final long fulltime) {
		StringBuilder builder = new StringBuilder();
		long time = fulltime;
		if (time > HOURS_MS) {
			long hours = time / HOURS_MS;
			time = time % HOURS_MS;
			builder.append(hours).append('h');
			if (time > 0) {
				builder.append(' ');
			}
		}
		if (time > MIN_MS) {
			long min = time / MIN_MS;
			time = time % MIN_MS;
			builder.append(min).append('m');
			if (time > 0) {
				builder.append(' ');
			}
		}
		if (time > SEC_MS) {
			long sec = time / SEC_MS;
			time = time % SEC_MS;
			builder.append(sec).append('s');
			if (time > 0) {
				builder.append(' ');
			}
		}
		if (time > 0) {
			builder.append(time).append("ms");
		}
		return builder.toString();
	}
}
