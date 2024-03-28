public class TreeNumberGenerator {

    private static final int MAX_VALUE = 9999;
    private static final String VALID_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ23456789";

    /**
     * 生成下一个序列号。
     * 给定一个当前序列号，该方法会生成一个按逻辑顺序递增的下一个序列号。
     *
     * @param currentNumber 当前序列号，格式为"xx-xx-xx-..."，其中"xx"为任意数字。
     * @return 返回按逻辑顺序递增的下一个序列号，格式与输入相同。
     * @throws IllegalArgumentException 如果输入的序列号格式不正确。
     */
    public static String generateNextNumber(String currentNumber) throws IllegalArgumentException {
        validateNumber(currentNumber); // 验证序列号的格式是否正确

        StringBuilder nextNumber = new StringBuilder(currentNumber);

        // 将序列号根据"-"分割成多个部分
        String[] parts = currentNumber.split("-");
        int lastIndex = parts.length - 1; // 获取最后一个部分的索引

        // 获取最后一个部分的值，并对其进行递增处理
        String lastPart = parts[lastIndex];
        String incrementedPart = incrementPart(lastPart);

        // 将递增后的一部分替换回原数组
        parts[lastIndex] = incrementedPart;

        // 重新组合序列号
        nextNumber = new StringBuilder(String.join("-", parts));

        return nextNumber.toString();
    }

    /**
     * 验证一个字符串是否为有效的数字。
     * 有效的数字由大写字母、数字和破折号组成，且不能包含字符"0"和"1"。
     *
     * @param number 待验证的字符串。
     * @throws IllegalArgumentException 如果字符串包含无效字符或"0"、"1"字符。
     */
    private static void validateNumber(String number) throws IllegalArgumentException {
        // 验证字符串是否只包含大写字母、数字和破折号
        if (!number.matches("^[A-Z0-9-]+$")) {
            throw new IllegalArgumentException("Invalid characters in the number");
        }

        // 检查字符串是否包含"0"或"1"
        if (number.contains("0") || number.contains("1")) {
            throw new IllegalArgumentException("Number contains invalid characters");
        }
    }

    /**
     * 递增给定字符串部分的值。
     * 此方法假设给定的字符串部分只包含有效字符，并且在递增后保持字符的顺序不变。
     * 当字符串部分中的最后一个字符是有效字符集中的最大字符时，该字符将被替换为有效字符集中的最小字符。
     * 如果字符串部分中的第一个字符就需要被替换为有效字符集中的最小字符，则表示字符串部分的值已经超过了最大值，
     * 并且会抛出IllegalArgumentException异常。
     *
     * @param part 需要递增的字符串部分。该字符串只包含有效字符。
     * @return 递增后的字符串部分。
     * @throws IllegalArgumentException 如果字符串部分包含无效字符或者递增后字符串部分的值超过了最大值。
     */
    private static String incrementPart(String part) throws IllegalArgumentException {
        char[] chars = part.toCharArray(); // 将字符串转换为字符数组，以便逐个字符处理。
        for (int i = chars.length - 1; i >= 0; i--) { // 从字符串的末尾开始遍历。
            char c = chars[i];
            int index = VALID_CHARS.indexOf(c); // 查找当前字符在有效字符集中的索引。
            if (index == -1) { // 如果字符不在有效字符集中，则抛出异常。
                throw new IllegalArgumentException("Invalid characters in the part");
            }
            if (index == VALID_CHARS.length() - 1) { // 如果当前字符是有效字符集中最大的字符。
                chars[i] = VALID_CHARS.charAt(0); // 将该字符替换为有效字符集中的最小字符。
                if (i == 0) { // 如果当前字符是字符串的第一个字符，则表示值已超过最大值，抛出异常。
                    throw new IllegalArgumentException("Number has exceeded the maximum value");
                }
            } else { // 如果当前字符不是有效字符集中最大的字符，将其替换为下一个字符并结束循环。
                chars[i] = VALID_CHARS.charAt(index + 1);
                break;
            }
        }
        return new String(chars); // 将更新后的字符数组转换回字符串并返回。
    }
    public static void main(String[] args) {
        try {
            System.out.println(generateNextNumber("AAAA")); // Output: AAAB
            System.out.println(generateNextNumber("AAAA-AAAA")); // Output: AAAA-AAAB
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}

