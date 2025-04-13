package io.cdap.wrangler.api.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses values like 10KB, 20MB, 5GB, etc.
 */
public class ByteSize {
  private static final Pattern PATTERN = Pattern.compile("(\\d+)(B|KB|MB|GB|TB)", Pattern.CASE_INSENSITIVE);

  public static long parse(String input) {
    Matcher matcher = PATTERN.matcher(input.trim());
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Invalid byte size: " + input);
    }

    long value = Long.parseLong(matcher.group(1));
    String unit = matcher.group(2).toUpperCase();

    switch (unit) {
      case "B": return value;
      case "KB": return value * 1024;
      case "MB": return value * 1024 * 1024;
      case "GB": return value * 1024 * 1024 * 1024;
      case "TB": return value * 1024L * 1024 * 1024 * 1024;
      default: throw new IllegalArgumentException("Unknown byte unit: " + unit);
    }
  }
}
