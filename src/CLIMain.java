
import ca.CA;
import java.io.BufferedWriter;
import java.io.IOException;
import myLib.utils.FileIO;

/**
 * コマンドラインからのCAクラスの実行
 *
 * @author tadaki
 */
public class CLIMain {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int n = 100;//セル数
        int rule = 90;//ルール番号
        int tmax = 100;//繰り返し時間
        CA ca = new CA(n, rule);//CAインスタンスを生成
        ca.initialSingle();//一つだけ1にした初期条件
        try (BufferedWriter out = FileIO.openWriter("output.txt")) {
            out.write(ca.state2String());
            out.newLine();
            for (int t = 0; t < tmax; t++) {
                ca.update();
                out.write(ca.state2String());
                out.newLine();
            }
        }
    }

}
