class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int before = 0;
        int max_health = health;
        for (int i = 0; i < attacks.length; i++) {
            int time = attacks[i][0] - before - 1;
            if (time >= bandage[0]) {
                health += (bandage[2] * time / bandage[0]);
            }
            health += (time * bandage[1]);
            if (health > max_health) {
                health = max_health;
            }
            
            health -= attacks[i][1];
            before = attacks[i][0];
            if (health <= 0) {
                return -1;
            }
        }
        return health;
    }
}