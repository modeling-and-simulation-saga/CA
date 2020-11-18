package rule184;

import ca.CA;
import java.awt.geom.Point2D;
import java.util.List;
import myLib.utils.Utils;

/**
 * 観測量を表す抽象クラス
 *
 * 密度を変えながらシミュレーションを実施しその結果（流量、速度）を得る
 *
 * @author tadaki
 */
abstract public class Observable {

    protected CA ca;

    /**
     * コンストラクタ
     *
     * @param n サイト数
     */
    public Observable(int n) {
        ca = new CA(n, 184);
    }

    /**
     * 平均量の取得
     *
     * @param p 車両密度
     * @param tmax 平均を行う時間
     * @return 平均量
     */
    abstract public double calcValue(double p, int tmax);

    /**
     * 平均量の取得
     *
     * @param dp 密度の間隔
     * @param tmax 平均を行う時間
     * @return 密度に対する平均量のリスト
     */
    public List<Point2D.Double> calcValues(double dp, int tmax) {
        List<Point2D.Double> pList = Utils.createList();
        int k = (int) (1. / dp);
//        pList.add(new Point2D.Double(0., 0.));
        for (int i = 1; i < k; i++) {
            double p = i * dp;//密度
            double value = calcValue(p, tmax);//平均量
            pList.add(new Point2D.Double(p, value));
        }
        pList.add(new Point2D.Double(1., 0.));
        return pList;
    }

    /**
     * 初期化して緩和
     * @param p 密度
     * @param tmax 緩和させる時間
     */
    protected void initializeAndRelax(double p, int tmax) {
        ca.initialize(p);//初期化
        //緩和
        for (int t = 0; t < tmax; t++) {
            ca.update();
        }
    }
}
