package de.dhbw.arukone;

/**
 * created by Karsten Köhler on 21.02.2016
 */
public class XmlCreator {
    public static void main(String... args) {
        prepare();
    }

    private static void prepare () {
        String string = "- - - - - - - - - - - -\n" +
                "- - - - - - - - - - 6 -\n" +
                "- - 1 - - 2 4 - - - - -\n" +
                "- - - - - - 3 - - - - -\n" +
                "- - 5 - - - - - - - - -\n" +
                "- - - - - - - 1 - - - -\n" +
                "- 2 8 6 - - - - - - - -\n" +
                "- - - - - - - - - - - -\n" +
                "- - - - 7 3 - - - - - -\n" +
                "- - 4 - - - - - - - - -\n" +
                "- - - - - - - - 8 5 7 -\n" +
                "- - - - - - - - - - - -";
        String[] lines = string.split("\n");
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        System.out.printf("<board size=\"%d\">\n", lines.length);

        for (int i = 1; i < 20; i++) {
            System.out.printf("<path id=\"%d\">\n", i);
            boolean start = true;
            for (int j = 0; j < lines.length; j++) {
                String line = lines[j];
                String chars[] = line.split(" ");

                for (int k = 0; k < chars.length; k++) {
                    String c = chars[k];
                    if (c.matches(String.valueOf(i))) {
                        String value = start ? "start" : "end";
                        System.out.printf("<point class=\"%s\">\n", value);
                        System.out.printf("<x>%d</x>\n<y>%d</y>\n", j, k);
                        System.out.println("</point>");
                        start = false;
                    }
                }


            }
            System.out.println("</path>");
        }

        System.out.println("</board>");
    }
}
