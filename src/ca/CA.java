package ca;

import myLib.utils.Utils;

/**
 * Walframの1次元セルオートマトン 周期境界条件
 *
 * @author tadaki
 */
public class CA {

    private final int cells[];//状態を表す配列
    private final int n;//セルの総数
    private int[] ruleMap;//ルール番号に対応した写像

    private int numDifference = 0;//一回の更新で値に変更のあったセルの数
    private int numR;//初期に値が1であるセルの数

    /**
     * コンストラクタ
     *
     * @param n セル総数
     * @param rule ルール番号
     */
    public CA(int n, int rule) {
        this.n = n;
        cells = new int[n];
        rule = rule % 256;
        ruleMap = mkRuleMap(rule);//ルールを表す写像の設置
    }

    /**
     * ルール番号の設定
     *
     * @param rule ルール番号
     */
    public void setRule(int rule) {
        ruleMap = mkRuleMap(rule);
    }

    /**
     * ルール番号に対応した写像を作成
     *
     * @param ruleNumber
     * @return 写像
     */
    public static int[] mkRuleMap(int ruleNumber) {
        int newRuleMap[] = new int[8];
        for (int i = 0; i < 8; i++) {//ruleNumberの下位ビットから調べる
            //最下位ビットの値を求める

            //その値をnewRuleMap[i]に代入する

            //右に1ビットシフト（2で割る）

        }
        return newRuleMap;
    }

    /**
     * 初期化 確率1/2で0と1をでたらめにばらまく
     */
    public void initialize() {
        initialize(0.5);
    }

    /**
     * 初期化 確率rで1をでたらめにばらまく
     *
     * @param r 1 とする確率
     */
    public void initialize(double r) {
        numR = (int) (n * r);
        int k = 0;
        for (int i = 0; i < n; i++) {
            cells[i] = 0;
        }
        if (r <= 0) {
            initialSingle();
        } else {
            //0 からn-1までの数値をでたらめな順番に並べた配列を返す
            int ar[] = Utils.createRandomNumberList(n);
            for (int i = 0; i < numR; i++) {
                cells[ar[i]] = 1;
            }
        }
    }

    /**
     * 中央だけを1とする初期化
     */
    public void initialSingle() {
        int k = n / 2;
        for (int i = 0; i < cells.length; i++) {
            cells[i] = 0;
        }
        cells[k] = 1;
    }

    /**
     * 状態更新
     *
     * @return 状態が更新された新しい状態
     */
    public int[] update() {
        int cellsDummy[] = new int[n];//ダミーの状態
        for (int i = 0; i < n; i++) {//このループの中を記述
            //左右のセルの番号を求める（周期境界に注意）
            //右側
            //左側
            //対象となるセル及び左右のセルの状態を10進数kに変換
            //ruleMap[k]が次の時刻の状態に対応

            //cellsDummyに次の状態を設定

        }

        //状態が変わったセルの数を数える
        numDifference = 0;
        for (int i = 0; i < n; i++) {
            numDifference += (cells[i] + cellsDummy[i]) % 2;
        }
        //ダミーをシステムへ複写
        System.arraycopy(cellsDummy, 0, cells, 0, n);
        return cellsDummy;
    }

    /**
     * 状態を文字列に変換：0->" ", 1->"*";
     *
     * @return
     */
    public String state2String() {
        String symbol[] = {" ", "*"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cells.length; i++) {
            sb.append(symbol[cells[i]]);
        }
        return sb.toString();
    }

    /**
     * ruleMapを確認するための文字列生成
     *
     * @param ruleMapDummy
     * @return
     */
    public static String ruleMap2String(int ruleMapDummy[]) {
        //改行コード(OS毎に異なる)
        String nl = System.getProperty("line.separator");

        StringBuilder sb = new StringBuilder();
        sb.append("rule-").append(ruleMap2Int(ruleMapDummy)).append(nl);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    int r = 4 * i + 2 * j + k;
                    sb.append("(").append(i).append(j).append(k).append(")->");
                    sb.append(ruleMapDummy[r]);
                    sb.append(nl);
                }
            }
        }
        return sb.toString();
    }

    /**
     * ruleMapを整数に変換
     *
     * @param ruleMapDummy
     * @return
     */
    public static int ruleMap2Int(int ruleMapDummy[]) {
        int k = 0;
        int m = 1;
        for (int i = 0; i < 8; i++) {
            k += m * ruleMapDummy[i];
            m *= 2;
        }
        return k;
    }

    //**** setters and getters
    public int[] getCells() {
        return cells;
    }

    public int getN() {
        return n;
    }

    public int getNumDifference() {
        return numDifference;
    }

    public int getNumR() {
        return numR;
    }

    public int[] getRuleMap() {
        return ruleMap;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 100;//セルの数
        int tmax = 100;//時間の最大値
        int ruleNumber = 184;//ルール番号
        CA sys = new CA(n, ruleNumber);
        sys.initialize(0.6);
        //ruleSetを印刷
        System.out.println(CA.ruleMap2String(sys.getRuleMap()));
    }
}
