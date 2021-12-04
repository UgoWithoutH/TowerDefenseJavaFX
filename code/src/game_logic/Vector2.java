package game_logic;

public class Vector2 {

        // Members
        private float x;
        private float y;

        // Constructors
        public Vector2() {
            this.x = 0.0f;
            this.y = 0.0f;
        }

        public Vector2(float x, float y) {
            this.x = x;
            this.y = y;
        }

        // Compare two vectors
        public boolean equals(game_logic.Vector2 other) {
            return (this.x == other.x && this.y == other.y);
        }

        public static double distance(game_logic.Vector2 a, game_logic.Vector2 b) {
            float v0 = b.x - a.x;
            float v1 = b.y - a.y;
            return Math.sqrt(v0*v0 + v1*v1);
        }

        public void normalize() {
            // sets length to 1
            //
            double length = Math.sqrt(x*x + y*y);

            if (length != 0.0) {
                float s = 1.0f / (float)length;
                x = x*s;
                y = y*s;
            }
        }

}
