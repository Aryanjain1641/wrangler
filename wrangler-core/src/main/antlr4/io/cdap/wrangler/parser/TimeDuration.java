package io.cdap.wrangler.api.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses values like 150ms, 2s, 5min, 1h, etc.
 */
public class TimeDuration {
  private static final Pattern PATTERN = Pattern.compile("(\\d+)(ms|s|min|h|d)", Pattern.CASE_INSENSITIVE);

  public static long parseMillis(String input) {
    Matcher matcher = PATTERN.matcher(input.trim());
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Invalid time duration: " + input);
    }

    long value = Long.parseLong(matcher.group(1));
    String unit = matcher.group(2).toLowerCase();

    switch (unit) {
      case "ms": return value;
      case "s": return value * 1000;
      case "min": return value * 60 * 1000;
      case "h": return value * 60 * 60 * 1000;
      case "d": return value * 24 * 60 * 60 * 1000;
      default: throw new IllegalArgumentException("Unknown time unit: " + unit);
    }
  }
}
