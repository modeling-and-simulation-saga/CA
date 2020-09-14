package rule184;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import myLib.utils.FileIO;

/**
 * 流量を求めるクラス
 *
 * @author tadaki
 */
public class Flow extends Observable {

    /**
     * セルの数を与えて初期化するコンストラクタ
     *
     * @param n
     */
    public Flow(int n) {
        super(n);
    }

    /**
     * 流量を求める
     *
     * @param p 車両密度
     * @param tmax 観測までの緩和時間
     * @return 流量」
     */
    @Override
    public double calcValue(double p, int tmax) {
        initializeAndRelax(p,tmax);//緩和
        double flow = 0.;
        //流量を返す
        int num = ca.getNumDifference() / 2;//移動した車両数
        int numSite = ca.getN();//サイト数
        flow = ((double) num) / numSite;
        return flow;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        int n = 100;
        double dp = 0.01;
        int tmax=100*n;
        Flow sys = new Flow(n);
        List<Point2D.Double> points = sys.calcValues(dp,tmax);
        try (BufferedWriter out = FileIO.openWriter("fundamental.txt")) {
            for (Point2D.Double p : points) {
                FileIO.writeSSV(out, p.x, p.y);
            }
        }
    }

}
