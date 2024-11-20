import java.util.ArrayList;
import java.util.List;

public class PasswordStrength {

    private int score;
    List<String> feedbackMessages;

    public PasswordStrength() {
        score = 0;
        feedbackMessages = new ArrayList<>();
    }


    public void addMessage(String message) {
        feedbackMessages.add(message);
    }

    public void addPts(int pts) {
        score += pts;
    }

    public int getScore() {
        return score;
    }

    public List<String> getFeedbackMessages() {
        return feedbackMessages;
    }

    public void merge(PasswordStrength passwordStrength){
        score+=passwordStrength.score;
        passwordStrength.feedbackMessages.stream().forEach(msg->feedbackMessages.add(msg));
    }


    public PassStrengthLevel calculateStrength(){
        if(score<=5) return PassStrengthLevel.WEAK;
        else if(score==6 || score==7) return PassStrengthLevel.MEDIUM;
        else return PassStrengthLevel.STRONG;
    }

}
