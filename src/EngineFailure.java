public class EngineFailure {


    private String discription;


    public String getDiscription() {
        discription = """
                Alert!
                Critical failure in engines!
                """;
        return discription;
    }

    public boolean engineFailureStatus() {
        double random = Math.random();
        if (random > 0.5) {
            return true;
        } else {
            return false;
        }
    }


}
