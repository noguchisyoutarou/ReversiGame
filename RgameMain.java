import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RgameMain {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		/* 盤面管理用の配列と入力用の変数を用意*/
		int field[][] = {
				{2, 2, 2, 2, 2, 1, 2, 2}, 
				{2, 2, 1, 2, 1, 1, 1, 1}, 
				{2, 2, 2, 1, 2, 1, 1, 1}, 
				{2, 2, 1, 1, 1, 1, 1, 1}, 
				{2, 2, 2, 2, 1, 1, 1, 1}, 
				{2, 2, 2, 2, 2, 2, 1, 1},
				{2, 2, 2, 2, 2, 1, 1, 0},
				{2, 2, 2, 2, 2, 2, 2, 0},
		};
		String line; //先攻後攻指定、座標入力用
		int p;		//プレイヤー確認用　1=先攻　2=後攻
		int b = 0;		//２連続パス確認用(詰み検知) 
		String lineXs = null; //入力値２文字目格納
		String lineYs = null; //入力値1文字目格納
		boolean inputCheckResult; //入力値比較結果格納
		String comparecharA = "A"; 
		String comparecharB = "B";
		String comparecharC = "C";
		String comparecharD = "D";
		String comparecharE = "E";
		String comparecharF = "F";
		String comparecharG = "G";
		String comparecharH = "H";
		int lineXi = -1;
		int lineYi = -1;
		String enableYX;
		boolean fieldSpaceResult = true;
		int[] absoluteZero = {(0)};
		ArrayList<String> enablelist = new ArrayList<String>(); 
		/* 先攻後攻選択と分岐,例外処理*/
		System.out.println("このプログラムはオセロゲームです。");
		System.out.println("「先攻」か「後攻」のどちらかを入力して選択してください。");
		try {
			while (!((line = reader.readLine()).equals("先攻")) && !(line.equals("後攻"))) {
				System.out.println("それ以外を選択");//デバッグプリント
				System.out.println("入力が正しくありません。「先攻」か「後攻」を入力してください。");
			}
			if (line.equals("先攻")) {
				System.out.println("先攻を選択");		//デバッグプリント
				p = 1;
				while (b < 2 && fieldSpaceResult == true) {			//連続パス数ｂが2未満であり、fieldに0(空き)が存在する限り継続
					p = 1;
					fieldSpaceResult = false;
					for(int i = 0; i < 8; i++) {
						for(int n = 0; n < 8; n++) {
							if(field[i][n] == absoluteZero[0]){
								fieldSpaceResult = true;
							} 
						}
					}
					/*ゲーム中の配置可能座標確認・格納メソッド呼び出し*/
					enablelist = Rgame_tool.fieldSearch(field, p);
				
					/*パスか配置するか分岐*/
					if (enablelist.size() == 0) {			//配置可能座標無し、パス
						p = 2;
						b = b + 1;
						System.out.println("配置可能な座標が無いので\"先攻\"はパスします。");
						fieldSpaceResult = false;
						for(int i = 0; i < 8; i++) {
							for(int n = 0; n < 8; n++) {
								if(field[i][n] == absoluteZero[0]){
									fieldSpaceResult = true;
								} 
							}
						}
						/*ゲーム中の配置可能座標確認・格納メソッド呼び出し*/						
						enablelist = Rgame_tool.fieldSearch(field, p); //パス後相手配置可能座標確認
						
						if (enablelist.size() == 0 || b > 1) {					////配置可能座標無し、パス2連続成立
							b = b + 1;
							p = 1;
							System.out.println("配置可能な座標が無いので\"後攻\"はパスします。");
							fieldSpaceResult = false;
							for(int i = 0; i < 8; i++) {
								for(int n = 0; n < 8; n++) {
									if(field[i][n] == absoluteZero[0]){
										fieldSpaceResult = true;
									} 
								}
							}
						} else {
							/*NPC座標指定メソッド呼び出し*/
							line = Rgame_tool.npcDecide(enablelist);
							lineXs = line.substring(3, 4);		//入力値確認
							lineYs = line.substring(0, 1);
							lineYi = Integer.parseInt(lineYs);
							lineXi = Integer.parseInt(lineXs);
							
							/*反転計算・配列へ格納*/
							Rgame_tool.reverseCulculate(field, p, lineYi, lineXi);
							
							/*ゲーム中の盤面表示メソッド呼び出し*/
							Rgame_tool.fieldPrint(field);
							
							b = 0;				//連続パス数リセット
							p = 1;
							fieldSpaceResult = false;
							for(int i = 0; i < 8; i++) {
								for(int n = 0; n < 8; n++) {
									if(field[i][n] == absoluteZero[0]){
										fieldSpaceResult = true;
									} 
								}
							}
						}
					/***************************************先攻左分岐-完-**********************************************/
						
					} else {								//配置可能座標在り
						b = 0; 								//連続パス数リセット
						
						/*ゲーム中の盤面表示メソッド呼び出し*/
						Rgame_tool.fieldPrint(field);
						
						/*入力値受付、確認*/
						System.out.println("配置する座標を入力してください。例：A1");
							do {	
								try {
									line = reader.readLine();
									lineXs = null; 
									lineYs = null; 
									lineXi = -1;
									lineYi = -1;
									lineXs = line.substring(1, 2);		//入力値確認
									lineYs = line.substring(0, 1);
									lineXi =Integer.parseInt(lineXs);
									enableYX = null;
									if (line.length() > 2) {
										System.out.println("2文字で座標を指定してください。例：A２-64行目");
									} if (lineXi > 7) {
										System.out.println("X軸座標は0~7で指定してください。例：A２-67行目");							
									} if (!(lineYs.equalsIgnoreCase(comparecharA) || lineYs.equalsIgnoreCase(comparecharB) || lineYs.equalsIgnoreCase(comparecharC) || lineYs.equalsIgnoreCase(comparecharD) || lineYs.equalsIgnoreCase(comparecharE) || lineYs.equalsIgnoreCase(comparecharF) || lineYs.equalsIgnoreCase(comparecharG) || lineYs.equalsIgnoreCase(comparecharH))) {
											System.out.println("Y軸座標はA~Hで指定してください。例：A２-70行目");							
									}
									if (lineYs.equalsIgnoreCase(comparecharA)) {
											lineYi = 0;									
										} else if (lineYs.equalsIgnoreCase(comparecharB)) {
												lineYi = 1;
											} else if (lineYs.equalsIgnoreCase(comparecharC)) {
													lineYi = 2;
												} else if (lineYs.equalsIgnoreCase(comparecharD)) {
														lineYi = 3;
													} else if (lineYs.equalsIgnoreCase(comparecharE)) {
															lineYi = 4;
														}else if (lineYs.equalsIgnoreCase(comparecharF)) {
																lineYi = 5;
															}else if (lineYs.equalsIgnoreCase(comparecharG)) {
																	lineYi = 6;
																}else if (lineYs.equalsIgnoreCase(comparecharH)) {
																		lineYi = 7;
									}
								} catch (NumberFormatException e) {
									System.out.println("アルファベットと数字の組み合わせによる座標を指定してください。例：A２");
								} catch (StringIndexOutOfBoundsException e){
									System.out.println("2文字で座標を指定してください。例：A２");
								} 
								enableYX = lineYi + ", " + lineXi;
								inputCheckResult = Rgame_tool.inputCheck(enableYX, enablelist);
								if (inputCheckResult == false) {				
									System.out.println("その座標には配置できません。座標を再指定してください。");					
								}
							} while (!(inputCheckResult == true) || ((line.length() < 0 || line.length() > 2) || (lineXi < 0 || lineXi > 7) || !(lineYs.equalsIgnoreCase(comparecharA) || lineYs.equalsIgnoreCase(comparecharB) || lineYs.equalsIgnoreCase(comparecharC) || lineYs.equalsIgnoreCase(comparecharD) || lineYs.equalsIgnoreCase(comparecharE) || lineYs.equalsIgnoreCase(comparecharF) || lineYs.equalsIgnoreCase(comparecharG) || lineYs.equalsIgnoreCase(comparecharH))));	//入力受付座標入力
						
					
							/*反転計算・配列へ格納*/
							Rgame_tool.reverseCulculate(field, p, lineYi, lineXi);
							
							/*ゲーム中の盤面表示メソッド呼び出し*/
							Rgame_tool.fieldPrint(field);
							
							p = 2;
							/*ゲーム中の配置可能座標確認・格納メソッド呼び出し*/
							enablelist = Rgame_tool.fieldSearch(field, p);
							fieldSpaceResult = false;
							for(int i = 0; i < 8; i++) {
								for(int n = 0; n < 8; n++) {
									if(field[i][n] == absoluteZero[0]){
										fieldSpaceResult = true;
									}
								}
							}
							if (enablelist.size() == 0) {			//配置可能座標無し、パス
								p = 1;
								b = b + 1;
								if(fieldSpaceResult == false) {									
								} else {
									System.out.println("配置可能な座標が無いので\"後攻\"はパスします。");
								}
							} else {
									/*NPC座標指定メソッド呼び出し*/
									line = Rgame_tool.npcDecide(enablelist);
									lineXs = line.substring(3, 4);		//入力値確認
									lineYs = line.substring(0, 1);
									lineYi =Integer.parseInt(lineYs);
									lineXi =Integer.parseInt(lineXs);
									
									/*反転計算・配列へ格納*/
									Rgame_tool.reverseCulculate(field, p, lineYi, lineXi);
									
									/*ゲーム中の盤面表示メソッド呼び出し*/
									Rgame_tool.fieldPrint(field);
									b = 0;
									p = 1;
									fieldSpaceResult = false;
									for(int i = 0; i < 8; i++) {
										for(int n = 0; n < 8; n++) {
											if(field[i][n] == absoluteZero[0]){
												fieldSpaceResult = true;
											} 
										}
									}
								}
						}
				}
				/***************************************先攻右分岐-完-**********************************************/
			} else {
				System.out.println("後攻を選択");		//デバッグプリント
				p = 1;
				while (b < 2 && fieldSpaceResult == true) {			//連続パス数ｂが2未満であり、fieldに0(空き)が存在する限り継続
					p = 1;
					fieldSpaceResult = false;
					for(int i = 0; i < 8; i++) {
						for(int n = 0; n < 8; n++) {
							if(field[i][n] == absoluteZero[0]){
								fieldSpaceResult = true;
							} 
						}
					}
					/*ゲーム中の配置可能座標確認・格納メソッド呼び出し*/
					enablelist = Rgame_tool.fieldSearch(field, p);
				
					/*パスか配置するか分岐*/
					if (enablelist.size() == 0) {			//配置可能座標無し、パス
						p = 2;
						b = b + 1;
						System.out.println("配置可能な座標が無いので\"先攻\"はパスします。");

						/*ゲーム中の配置可能座標確認・格納メソッド呼び出し*/						
						enablelist = Rgame_tool.fieldSearch(field, p); //パス後相手配置可能座標確認
						
						if (enablelist.size() == 0 || b > 1) {					////配置可能座標無し、パス2連続成立
							b = b + 1;
							p = 1;
							System.out.println("配置可能な座標が無いので\"後攻\"はパスします。-253");
							fieldSpaceResult = false;
							for(int i = 0; i < 8; i++) {
								for(int n = 0; n < 8; n++) {
									if(field[i][n] == absoluteZero[0]){
										fieldSpaceResult = true;
									} 
								}
							}
						} else {
							/*ゲーム中の盤面表示メソッド呼び出し*/
							Rgame_tool.fieldPrint(field);
							
							/*入力値受付、確認*/
							System.out.println("配置する座標を入力してください。例：A1");
								do {	
									try {
										line = reader.readLine();
										lineXs = null; 
										lineYs = null; 
										lineXi = -1;
										lineYi = -1;
										lineXs = line.substring(1, 2);		//入力値確認
										lineYs = line.substring(0, 1);
										lineXi =Integer.parseInt(lineXs);
										enableYX = null;
										if (line.length() > 2) {
											System.out.println("2文字で座標を指定してください。例：A２");
										} if (lineXi > 7) {
											System.out.println("X軸座標は0~7で指定してください。例：A２");							
										} if (!(lineYs.equalsIgnoreCase(comparecharA) || lineYs.equalsIgnoreCase(comparecharB) || lineYs.equalsIgnoreCase(comparecharC) || lineYs.equalsIgnoreCase(comparecharD) || lineYs.equalsIgnoreCase(comparecharE) || lineYs.equalsIgnoreCase(comparecharF) || lineYs.equalsIgnoreCase(comparecharG) || lineYs.equalsIgnoreCase(comparecharH))) {
												System.out.println("Y軸座標はA~Hで指定してください。例：A２");							
										}
										if (lineYs.equalsIgnoreCase(comparecharA)) {
												lineYi = 0;									
											} else if (lineYs.equalsIgnoreCase(comparecharB)) {
													lineYi = 1;
												} else if (lineYs.equalsIgnoreCase(comparecharC)) {
														lineYi = 2;
													} else if (lineYs.equalsIgnoreCase(comparecharD)) {
															lineYi = 3;
														} else if (lineYs.equalsIgnoreCase(comparecharE)) {
																lineYi = 4;
															}else if (lineYs.equalsIgnoreCase(comparecharF)) {
																	lineYi = 5;
																}else if (lineYs.equalsIgnoreCase(comparecharG)) {
																		lineYi = 6;
																	}else if (lineYs.equalsIgnoreCase(comparecharH)) {
																			lineYi = 7;
										}
									} catch (NumberFormatException e) {
										System.out.println("アルファベットと数字の組み合わせによる座標を指定してください。例：A２");
									} catch (StringIndexOutOfBoundsException e){
										System.out.println("2文字で座標を指定してください。例：A２");
									} 
									enableYX = lineYi + ", " + lineXi;
									inputCheckResult = Rgame_tool.inputCheck(enableYX, enablelist);
									if (inputCheckResult == false) {
										System.out.println("その座標には配置できません。座標を再指定してください。");					
									}
								} while (!(inputCheckResult == true) || ((line.length() < 0 || line.length() > 2) || (lineXi < 0 || lineXi > 7) || !(lineYs.equalsIgnoreCase(comparecharA) || lineYs.equalsIgnoreCase(comparecharB) || lineYs.equalsIgnoreCase(comparecharC) || lineYs.equalsIgnoreCase(comparecharD) || lineYs.equalsIgnoreCase(comparecharE) || lineYs.equalsIgnoreCase(comparecharF) || lineYs.equalsIgnoreCase(comparecharG) || lineYs.equalsIgnoreCase(comparecharH))));	//入力受付座標入力
							
						
								/*反転計算・配列へ格納*/
								Rgame_tool.reverseCulculate(field, p, lineYi, lineXi);
								
								/*ゲーム中の盤面表示メソッド呼び出し*/
								Rgame_tool.fieldPrint(field);
								b = 0;
								p = 1;
								fieldSpaceResult = false;
								for(int i = 0; i < 8; i++) {
									for(int n = 0; n < 8; n++) {
										if(field[i][n] == absoluteZero[0]){
											fieldSpaceResult = true;
										} 
									}
								}
							}
					} else {	
						/*NPC座標指定メソッド呼び出し*/
						line = Rgame_tool.npcDecide(enablelist);
						lineXs = line.substring(3, 4);		//入力値確認
						lineYs = line.substring(0, 1);
						lineYi =Integer.parseInt(lineYs);
						lineXi =Integer.parseInt(lineXs);
						
						/*反転計算・配列へ格納*/
						Rgame_tool.reverseCulculate(field, p, lineYi, lineXi);
						
						/*ゲーム中の盤面表示メソッド呼び出し*/
						Rgame_tool.fieldPrint(field);
						p = 2;
						b = 0;
						/*ゲーム中の配置可能座標確認・格納メソッド呼び出し*/
						enablelist = Rgame_tool.fieldSearch(field, p);
						if (enablelist.size() == 0) {			//配置可能座標無し、パス
							fieldSpaceResult = false;
							for(int i = 0; i < 8; i++) {
								for(int n = 0; n < 8; n++) {
									if(field[i][n] == absoluteZero[0]){
										fieldSpaceResult = true;
									}
								}
							}
							p = 1;
							b = b + 1;
							if(fieldSpaceResult == false) {
							} else {
							System.out.println("配置可能な座標が無いので\"後攻\"はパスします。");
							}
						} else {
						/*入力値受付、確認*/
						System.out.println("配置する座標を入力してください。例：A1");
							do {	
								try {
									line = reader.readLine();
									lineXs = null; 
									lineYs = null; 
									lineXi = -1;
									lineYi = -1;
									lineXs = line.substring(1, 2);		//入力値確認
									lineYs = line.substring(0, 1);
									lineXi =Integer.parseInt(lineXs);
									enableYX = null;
									if (line.length() > 2) {
										System.out.println("2文字で座標を指定してください。例：A２");
									} if (lineXi > 7) {
										System.out.println("X軸座標は0~7で指定してください。例：A２");							
									} if (!(lineYs.equalsIgnoreCase(comparecharA) || lineYs.equalsIgnoreCase(comparecharB) || lineYs.equalsIgnoreCase(comparecharC) || lineYs.equalsIgnoreCase(comparecharD) || lineYs.equalsIgnoreCase(comparecharE) || lineYs.equalsIgnoreCase(comparecharF) || lineYs.equalsIgnoreCase(comparecharG) || lineYs.equalsIgnoreCase(comparecharH))) {
											System.out.println("Y軸座標はA~Hで指定してください。例：A２");							
									}
									if (lineYs.equalsIgnoreCase(comparecharA)) {
											lineYi = 0;									
										} else if (lineYs.equalsIgnoreCase(comparecharB)) {
												lineYi = 1;
											} else if (lineYs.equalsIgnoreCase(comparecharC)) {
													lineYi = 2;
												} else if (lineYs.equalsIgnoreCase(comparecharD)) {
														lineYi = 3;
													} else if (lineYs.equalsIgnoreCase(comparecharE)) {
															lineYi = 4;
														}else if (lineYs.equalsIgnoreCase(comparecharF)) {
																lineYi = 5;
															}else if (lineYs.equalsIgnoreCase(comparecharG)) {
																	lineYi = 6;
																}else if (lineYs.equalsIgnoreCase(comparecharH)) {
																		lineYi = 7;
									}
								} catch (NumberFormatException e) {
									System.out.println("アルファベットと数字の組み合わせによる座標を指定してください。例：A２");
								} catch (StringIndexOutOfBoundsException e){
									System.out.println("2文字で座標を指定してください。例：A２");
								} 
								enableYX = lineYi + ", " + lineXi;
								inputCheckResult = Rgame_tool.inputCheck(enableYX, enablelist);
								if (inputCheckResult == false) {
									System.out.println("その座標には配置できません。座標を再指定してください。");					
								}
							} while (!(inputCheckResult == true) || ((line.length() < 0 || line.length() > 2) || (lineXi < 0 || lineXi > 7) || !(lineYs.equalsIgnoreCase(comparecharA) || lineYs.equalsIgnoreCase(comparecharB) || lineYs.equalsIgnoreCase(comparecharC) || lineYs.equalsIgnoreCase(comparecharD) || lineYs.equalsIgnoreCase(comparecharE) || lineYs.equalsIgnoreCase(comparecharF) || lineYs.equalsIgnoreCase(comparecharG) || lineYs.equalsIgnoreCase(comparecharH))));	//入力受付座標入力
				
							/*反転計算・配列へ格納*/
							Rgame_tool.reverseCulculate(field, p, lineYi, lineXi);
							
							/*ゲーム中の盤面表示メソッド呼び出し*/
							Rgame_tool.fieldPrint(field);
							b = 0;
							p = 1;
							fieldSpaceResult = false;
							for(int i = 0; i < 8; i++) {
								for(int n = 0; n < 8; n++) {
									if(field[i][n] == absoluteZero[0]){
										fieldSpaceResult = true;
									} 
								}
							}
						}
					}
				}	
			}
		} catch (IOException e) {
			System.out.println("IOErrer" + e);
		}
		
		/*ゲーム後の結果計算メソッド呼び出し*/
		Rgame_tool.fieldCount(field);
		
	}	
}
