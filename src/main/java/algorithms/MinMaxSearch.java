package algorithms;

import models.BoxOwner;
import models.MinMaxEnum;
import models.Node;

public class MinMaxSearch {
    // This will build the scored game tree and will be used to make the next move for the AI
    public static void searchGameTreeForMove(Node node) {
        for (Node child : node.getChildren()) {
            // Check parent score
            Node parent = node.getParent();
            if (parent != null) {
                // Check if it has a score and if current node has a score
                if (parent.getScore() != null && node.getScore() != null) {
                    int parentScore = parent.getScore().getScore();
                    int currentScore = node.getScore().getScore();
                    // If parent node is MAX then check if my current score is less than parent score, if so then I can quit out of branch
                    // I already will never get a node larger than currentScore since current node is a MIN node
                    if (parent.getMinOrMax().equals(MinMaxEnum.MAX) && currentScore < parentScore) {
                        break;
                    }
                    // If parent node is MIN then check if my current score is greater than parent score, if so then I can quit out of branch
                    // I already will never get a node smaller than currentScore since current node is a MAX node
                    else if (parent.getMinOrMax().equals(MinMaxEnum.MIN) && currentScore > parentScore) {
                        break;
                    }
                }
            }

            searchGameTreeForMove(child);

            // Calculate current score
            if (node.getScore() == null) {
                node.setScore(child.getScore().getScore());
            } else {
                int score = node.getScore().getScore();
                int childScore = child.getScore().getScore();
                // Check to see if the current node is a MIN node if so then see if the child score is less than the current score
                // If so, then set the current score to the child score as its now the lowest score for this branch
                if (node.getMinOrMax().equals(MinMaxEnum.MIN) && childScore < score) {
                    node.setScore(childScore);
                }
                // Check to see if the current node is a MAX node if so then see if the child score is greater than the current score
                // If so, then set the current score to the child score as its now the greatest score for this branch
                else if (node.getMinOrMax().equals(MinMaxEnum.MAX) && childScore > score) {
                    node.setScore(childScore);
                }
            }
        }

        // Calculate score
        if (node.getChildren().size() == 0) {
            // Leaf node and calculate score
            // Score of AI (Black)
            int blackScore = node.getBoard().calculateScoreForPlayer(BoxOwner.AI);
            // Score of Human (White)
            int whiteScore = node.getBoard().calculateScoreForPlayer(BoxOwner.HUMAN);
            int score = scoringFunction(blackScore, whiteScore);
            node.setScore(score);
        }

        // Check to see if this should be the parent score as well
        int currentScore = node.getScore().getScore();
        Node parent = node.getParent();
        if (parent != null) {
            // Check if it has a score
            if (parent.getScore() != null) {
                int parentScore = parent.getScore().getScore();
                // If parent node is a MIN node, then check and see if currentScore is less than parent and set it
                if (parent.getMinOrMax().equals(MinMaxEnum.MIN) && currentScore < parentScore) {
                    parent.setScore(currentScore);
                }
                // If parent node is a MAX node, then check and see if currentScore is greater than parent and set it
                else if (parent.getMinOrMax().equals(MinMaxEnum.MAX) && currentScore > parentScore) {
                    parent.setScore(currentScore);
                }
            } else {
                parent.setScore(currentScore);
            }
        }
    }

    // This is the scoring heuristic that we will use for our MinMax leaf node score
    private static int scoringFunction(int blackScore, int whiteScore) {
        return blackScore - whiteScore;
    }
}
