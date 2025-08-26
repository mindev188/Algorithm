class Solution {
    public String solution(String polynomial) {
        int xCoefficient = 0;
        int constant = 0;
        
        for (String term : polynomial.split("\\+")) {
            term = term.trim();
            if (term.contains("x")) {
                xCoefficient += parseXCoefficient(term);
            } else {
                constant += Integer.parseInt(term);
            }
        }
        
        return buildResult(xCoefficient, constant);
    }
    
    private int parseXCoefficient(String term) {
        String coefficient = term.substring(0, term.indexOf("x")).trim();
        return coefficient.isEmpty() ? 1 : Integer.parseInt(coefficient);
    }
    
    private String buildResult(int xCoefficient, int constant) {
        StringBuilder result = new StringBuilder();
        
        if (xCoefficient > 0) {
            if (xCoefficient > 1) {
                result.append(xCoefficient);
            }
            result.append("x");
        }
        
        if (constant > 0) {
            if (xCoefficient > 0) {
                result.append(" + ");
            }
            result.append(constant);
        }
        
        return result.toString();
    }
}