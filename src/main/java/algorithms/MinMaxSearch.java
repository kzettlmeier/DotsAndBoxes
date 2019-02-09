package algorithms;

import models.BoxOwner;
import models.MinMaxEnum;
import models.Node;

import java.util.List;

public class MinMaxSearch {
    public static void searchGameTreeForMove(Node node) {
        for (Node child : node.getChildren()) {
            searchGameTreeForMove(child);
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
        } else {
            // Else look at children and figure out what the best score is
            List<Node> children = node.getChildren();
            if (node.getMinOrMax().equals(MinMaxEnum.MIN)) {
                // Choose minimum
                int minScore = -1;
                for (Node child : children) {
                    if (minScore == -1) {
                        minScore = child.getScore();
                    } else {
                        if (child.getScore() < minScore) {
                            minScore = child.getScore();
                        }
                    }
                }
                node.setScore(minScore);
            } else {
                // Choose maximum
                int maxScore = -1;
                for (Node child : children) {
                    if (maxScore == -1) {
                        maxScore = child.getScore();
                    } else {
                        if (child.getScore() > maxScore) {
                            maxScore = child.getScore();
                        }
                    }
                }
                node.setScore(maxScore);
            }
        }
    }

    private static int scoringFunction(int blackScore, int whiteScore) {
        return blackScore - whiteScore;
    }
}
