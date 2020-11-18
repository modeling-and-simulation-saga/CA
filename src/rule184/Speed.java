package rule184;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import myLib.utils.FileIO;

/**
 * 平均速度を求めるクラス
 *
 * @author tadaki
 */
public class Speed extends Observable {

    /**
     * セルの数を与えて初期化するコンストラクタ
     *
     * @param n
     */
    public Speed(int n) {
        super(n);
    }

    /**
     * 速度を求める
     *
     * @param p 車両密度
     * @param tmax 観測までの緩和時間
     * @return 平均速度
     */
    @Override
    public double calcValue(double p, int tmax) {
        initializeAndRelax(p, tmax);
        double v = 0.;//平均速度
        //平均速度を返す



        return v;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        int n = 100;
        double dp = 0.02;
        int tmax = 100 * n;
        Speed sys = new Speed(n);
        List<Point2D.Double> points = sys.calcValues(dp, tmax);
        try (BufferedWriter out = FileIO.openWriter("speed.txt")) {
            for (Point2D.Double p : points) {
                FileIO.writeSSV(out, p.x, p.y);
            }
        }
    }

}
