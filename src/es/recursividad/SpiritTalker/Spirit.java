package es.recursividad.SpiritTalker;

public class Spirit {
    public static final String NAME = "Spirit";
    private static boolean pressented = false;
    private static boolean break_info = false;
    private static int state = 0;

    public static boolean isPressented() {
        return pressented;
    }

    public static void setPressented(boolean aPressented) {
        pressented = aPressented;
    }

    public static boolean isBreak_info() {
        return break_info;
    }

    public static void setBreak_info(boolean aBreak_info) {
        break_info = aBreak_info;
    }

    public static int getState() {
        return state;
    }

    public static void setState(int aState) {
        state = aState;
    }
}
