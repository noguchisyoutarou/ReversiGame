package reRgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReRGameMain {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		/*変数宣言*/
		String line = "testnull"; //先攻後攻指定・座標・UnDo入力用
		int p = 0; 					  //先攻後攻判定用
		
		
		/****************************処理開始(先頭)****************************/
		
		
		/****************************先攻後攻選択(先頭)****************************/
		System.out.println("このプログラムはオセロゲームです。");
		System.out.println("「先攻」か「後攻」のどちらかを入力して選択してください。");
		do  {
			try {
			line = reader.readLine();
				if(line.equals("先攻")) {
					p = 1;
				} else if (line.equals("後攻")) {
					p = 2;
					} else {
						System.out.println("「先攻」か「後攻」のどちらかを入力して選択してください。");
					}
			} catch (Exception e){
				System.out.println("「先攻」か「後攻」のどちらかを入力して選択してください。");
			}
		} while(!(line.equals("先攻")) && !(line.equals("後攻"))) ;
	/****************************先攻後攻選択(末尾)****************************/
		
	/****************************先攻独自処理(先頭)****************************/

		
		if (line.equals("先攻")) {
			//盤面表示//
			Board.fieldPrint();
			p = 1;
			boolean i;
			do {
				i = Board.inputCheck(p);
				if (i == false) {
					System.out.println("その座標には配置できません。再度正しい座標を入力してください。");
				}
			}while(i == false);
			//反転計算//
			Board.reverseCulculate(p);
		}
	/****************************先攻独自処理(末尾)****************************/
		
	/****************************共通処理部分(先頭)****************************/
		while ((Board.fieldSearch(1).size() != 0 || Board.fieldSearch(2).size() != 0)) {
			//盤面表示//
			Board.fieldPrint();
			p = 2; //後攻指定
			if(Board.fieldSearch(p).size() == 0) {	
				System.out.println("コンピューター：配置できる場所が無いためパスしました。");
			} else {
				Board.npcDecide(p);
				Board.reverseCulculate(p);
			}
			//共通処理入力部分
			//盤面表示//
			Board.fieldPrint();
			p = 1;
			boolean i;
			if(Board.fieldSearch(p).size() == 0) {
				System.out.println("プレイヤー：配置できる場所が無いためパスしました。");
			} else {
				do {
					i = Board.inputCheck(p);
					if (i == false) {
						System.out.println("その座標には配置できません。再度正しい座標を入力してください。");
					}
				}while(i == false);
				//反転計算//
				Board.reverseCulculate(p);
			}
		}
	/****************************共通処理部分(末尾)****************************/
	
	/****************************結果表示部分(先頭)****************************/
	//盤面表示//
	Board.fieldPrint();
	Board.fieldCount();
	/****************************結果表示部分(末尾)****************************/
	}
}

