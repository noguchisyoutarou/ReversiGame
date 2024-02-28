package reRgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Board {
	/****************************ゲームの前提設定(先頭)***************************/
	private static int SIZE = 8; //盤面の一辺あたりマス数
	private static int[][] field = new int[SIZE][SIZE];
	private static int[][] unDoField = new int[SIZE][SIZE];
	private static int enableFieldPathY; 
	private static int enableFieldPathX;
	static {
		// 盤面の初期化
		for(int i = 0; i < SIZE; i++) {
			for(int n = 0; n < SIZE; n++) {
				field[i][n] = 0;
				unDoField[i][n] = 0;
			}
		}	//盤面中央４マスに初期値格納
		field[(SIZE - 1)/2 + 0][(SIZE - 1)/2 + 0] = 2; //可読性のため+0
		field[(SIZE - 1)/2 + 0][(SIZE - 1)/2 + 1] = 1;
		field[(SIZE - 1)/2 + 1][(SIZE - 1)/2 + 0] = 1;
		field[(SIZE - 1)/2 + 1][(SIZE - 1)/2 + 1] = 2;
		unDoField[(SIZE - 1)/2 + 0][(SIZE - 1)/2 + 0] = 2; //可読性のため+0
		unDoField[(SIZE - 1)/2 + 0][(SIZE - 1)/2 + 1] = 1;
		unDoField[(SIZE - 1)/2 + 1][(SIZE - 1)/2 + 0] = 1;
		unDoField[(SIZE - 1)/2 + 1][(SIZE - 1)/2 + 1] = 2;
	}
	/****************************ゲームの前提設定(末尾)***************************/
	
	
	/****************************ゲーム後の結果計算、結果表示(先頭)***************************/
		public static void fieldCount() {
		int a = 0;
		int b = 0;
			for (int y = 0; y < SIZE; y++) {
				for (int x = 0; x < SIZE; x++) {
					if (field[y][x] == 1) {
						a = a + 1;
					} else if (field[y][x] == 2) {
						b = b + 1;
					} else {
					}
				}
			}	
			System.out.println("先攻は" + a + "点、後攻は" + b + "点");
			if (a > b) {
				System.out.println("プレイヤーの勝ちです。");
			} else if (a < b) {
				System.out.println("コンピューターの勝ちです。");
			} else {
				System.out.println("引き分けです。");
			}		
		}
	/****************************ゲーム後の結果計算、結果表示(末尾)***************************/
	
	/****************************TEST用ゲーム中の盤面表示(先頭)***************************/
		public static void unDofieldPrint() {
			int a = 0;						//点数カウント用(先攻)
			int b = 0;						//点数カウント用(後攻)
			System.out.printf("%-2s", " ");
			for(int i = 0; i < SIZE; i++) {
				System.out.printf("%-2d", i);
			}
			System.out.println();
			char fieldY = 'A';
			for (int y = 0; y < SIZE; y++) {
				System.out.printf("%-2c", fieldY + y);
				for (int x = 0; x < unDoField[y].length; x++) {
					if (unDoField[y][x] == 0) {					//表示内容の置き換え
						System.out.printf("%-2c",'-');
					} else if (unDoField[y][x] == 1) {
						System.out.printf("%-2c",'o');
						a = a + 1;							//現在の点数カウント(先攻)
					} else if (unDoField[y][x] == 2) {
						System.out.printf("%-2c",'x');
						b = b + 1;							//現在の点数カウント(後攻)
					}
				}	
				System.out.println();
			}
			System.out.println("プレイヤー　=　o:" + a +"　コンピューター　=　x:" + b);
		}	
	/****************************TEST用ゲーム中の盤面表示(末尾)***************************/
		
	/****************************ゲーム中の盤面表示(先頭)***************************/
		public static void fieldPrint() {
			int a = 0;						//点数カウント用(先攻)
			int b = 0;						//点数カウント用(後攻)
			System.out.printf("%-2s", " ");
			for(int i = 0; i < SIZE; i++) {
				System.out.printf("%-2d", i);
			}
			System.out.println();
			char fieldY = 'A';
			for (int y = 0; y < SIZE; y++) {
				System.out.printf("%-2c", fieldY + y);
				for (int x = 0; x < field[y].length; x++) {
					if (field[y][x] == 0) {					//表示内容の置き換え
						System.out.printf("%-2c",'-');
					} else if (field[y][x] == 1) {
						System.out.printf("%-2c",'o');
						a = a + 1;							//現在の点数カウント(先攻)
					} else if (field[y][x] == 2) {
						System.out.printf("%-2c",'x');
						b = b + 1;							//現在の点数カウント(後攻)
					}
				}	
				System.out.println();
			}
			System.out.println("プレイヤー　=　o:" + a +"　コンピューター　=　x:" + b);
		}	
	/****************************ゲーム中の盤面表示(末尾)***************************/
	
	/****************************配置可能座標確認・格納(先頭)***************************/
		public static ArrayList<String> fieldSearch(int pn) {
			int p = pn;
			int y;
			int x;
			ArrayList<String> enablelist = new ArrayList<String>(); 
			for (y = 0; y < field.length; y++) {
				for (x = 0; x < field[y].length; x++) {								
					try {
						if ((field[y][x - 1] != p && field[y][x - 1] != 0) && field[y][x] == 0)  {  //配列の左側を調査
							outer:
								for (int i = 2; i < SIZE; i++) {
									if (field[y][x - i] == p) {
									enablelist.add(y + ","+ x);
									break outer;
									} else if (field[y][x - i] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[y][x + 1] != p && field[y][x + 1] != 0) && field[y][x] == 0)  {  //配列の右側を調査
							outer:
								for (int i = 2; i < SIZE; i++) {
									if (field[y][x + i] == p) {
									enablelist.add(y + ","+ x);
									break outer;
									} else if (field[y][x + i] == 0) {
										break outer;
									} else { 
									}
								}	
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[y - 1][x - 1] != p && field[y - 1][x - 1] != 0) && field[y][x] == 0)  {  //配列の左上側を調査
							outer:
								for (int i = 2; i < SIZE; i++) {
									if (field[y - i][x - i] == p) {
									enablelist.add(y + ","+ x);
									break outer;
									} else if (field[y - i][x - i] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[y - 1][x] != p && field[y - 1][x] != 0) && field[y][x] == 0)  {  //配列の上側を調査
							outer:
								for (int i = 2; i < SIZE; i++) {
									if (field[y - i][x] == p) {
									enablelist.add(y + ","+ x);
									break outer;
									} else if (field[y - i][x] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[y - 1][x + 1] != p && field[y - 1][x + 1] != 0) && field[y][x] == 0)  {  //配列の右上側を調査
							outer:
								for (int i = 2; i < SIZE; i++) {
									if (field[y - i][x + i] == p) {
									enablelist.add(y + ","+ x);
									break outer;
									} else if (field[y - i][x + i] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					
					}
					try {
						if ((field[y + 1][x - 1] != p && field[y + 1][x - 1] != 0) && field[y][x] == 0)  {  //配列の左下側を調査
							outer:
								for (int i = 2; i < SIZE; i++) {
									if (field[y + i][x - i] == p) {
									enablelist.add(y + ","+ x);
									break outer;
									} else if (field[y + i][x - i] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[y + 1][x] != p && field[y + 1][x] != 0) && field[y][x] == 0)  {  //配列の下側を調査
							outer:
								for (int i = 2; i < SIZE; i++) {
									if (field[y + i][x] == p) {
									enablelist.add(y + ","+ x);
									break outer;
									} else if (field[y + i][x] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[y + 1][x + 1] != p && field[y + 1][x + 1] != 0) && field[y][x] == 0)  {  //配列の右上側を調査
							outer:
								for (int i = 2; i < SIZE; i++) {
									if (field[y + i][x + i] == p) {
									enablelist.add(y + ","+ x);
									break outer;
									} else if (field[y + i][x + i] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
				}
			}
			return enablelist;
		}
	/****************************配置可能座標確認・格納(末尾)***************************/
		
	/****************************入力値と配置可能座標比較(先頭)***************************/
		public static boolean inputCheck (int pn) {
			enableFieldPathY = playerInputY();
			enableFieldPathX = playerInputX();
			String enableYXs = (enableFieldPathY + "," + enableFieldPathX);
			if(fieldSearch(pn).contains(enableYXs) == true) {
				for(int i =0; i < SIZE; i++) {		//unDo配列に戻す状態を保存
					for(int j = 0; j < SIZE; j++) {
						unDoField[i][j] = field[i][j];	
					}
				}
			}
			return fieldSearch(pn).contains(enableYXs);
		}
	/****************************入力値と配置可能座標比較(末尾)***************************/
		
	/****************************Y軸入力値受付、unDo入力受付(先頭)***************************/
		public static int playerInputY() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String lineYs = null;			
			int lineYi = -1;		
			int lineYci = 0;
			char COMPAREA = 'A';
			char[] lineYc = null;
			char lineYcAndI = ' '; 
			System.out.println("配置する座標の縦軸を入力してください。例：A");
			System.out.println("一手前に戻す際は「unDo」と入力してください。");
			do {	
				try {
					lineYs = reader.readLine();
					lineYc = lineYs.toCharArray();
					if(lineYs.equals("unDo") && field == unDoField) {
						System.out.println("一手前に戻せません。");
					} else if(lineYs.equals("unDo") && field != unDoField) {
						System.out.println("一手前に盤面を戻しました。");
						for(int i =0; i < SIZE; i++) {		//unDo配列に戻す状態を保存
							for(int j = 0; j < SIZE; j++) {
								field[i][j] = unDoField[i][j];	
							}
						}
						fieldPrint();
					} else {
					}
					outer:
					for(int i = 0; i < SIZE; i++) {
						lineYci = COMPAREA + i;
						lineYcAndI =(char)lineYci;
						if(lineYc[0] == lineYcAndI) {
						lineYi = i;
						break outer;
						} else {
						}
					}
				} catch (Exception e){
				} if (lineYi == -1) {
					System.out.printf("用意された盤面の範囲内で座標を指定してください。例：A~%c%n",(COMPAREA + SIZE - 1));
				}
			} while (lineYi == -1);	
			return lineYi;
		}
	/****************************Y軸入力値受付、確認(末尾)***************************/
			
	/****************************X軸入力値受付、確認(先頭)***************************/
		public static int playerInputX() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String lineXs = null;
			int lineXi = -1;		
			System.out.println("配置する座標の縦軸を入力してください。例：1");
			do {	
				try {
					lineXs = reader.readLine();
					lineXi = Integer.parseInt(lineXs);
				} catch (Exception e){
				} if (lineXi < 0 || lineXi > (SIZE - 1)) {
					System.out.println("用意された盤面の範囲内で座標を指定してください。例：0~" + (SIZE - 1));
				}
			} while (lineXi < 0 || lineXi > (SIZE - 1) );	
			return lineXi;
	}	
	/****************************反転計算・配列格納(先頭)***************************/
		public static void reverseCulculate (int pn) {
			
			field[enableFieldPathY][enableFieldPathX] = pn;		//配置座標へ代入
			int i;//繰り返し用変数
			int j;//繰り返し用変数
			try {
			outer:
			if (field[enableFieldPathY][enableFieldPathX - 1] != pn && field[enableFieldPathY][enableFieldPathX - 1] != 0) {				//左側計算
				for (i = 2;(field[enableFieldPathY][enableFieldPathX - i] != 0) && i < SIZE; i++) {	
					if(field[enableFieldPathY][enableFieldPathX - i] != 0 && field[enableFieldPathY][enableFieldPathX - i] != pn) {
					} else {
							for (j = 1; j < i; j++) {
								field[enableFieldPathY][enableFieldPathX - j] = pn;
							}
							break outer;
					}		
				}
			} 
			} catch (Exception e) {
			}
			try {
				outer:
				if (field[enableFieldPathY][enableFieldPathX + 1] != pn && field[enableFieldPathY][enableFieldPathX + 1] != 0) {				//右側計算
					for (i = 2;(field[enableFieldPathY][enableFieldPathX + i] != 0) && i < SIZE; i++) {	
						if(field[enableFieldPathY][enableFieldPathX + i] != 0 && field[enableFieldPathY][enableFieldPathX + i] != pn) {
						} else {
								for (j = 1; j < i; j++) {
									field[enableFieldPathY][enableFieldPathX + j] = pn;
								}
								break outer;
						}		
					}
				} 
				} catch (Exception e) {
				}
			try {
				outer:
				if (field[enableFieldPathY - 1][enableFieldPathX - 1] != pn && field[enableFieldPathY - 1][enableFieldPathX - 1] != 0) {				//左上側計算
					for (i = 2;(field[enableFieldPathY - i][enableFieldPathX - i] != 0) && i < SIZE; i++) {	
						if(field[enableFieldPathY - i][enableFieldPathX - i] != 0 && field[enableFieldPathY - i][enableFieldPathX - i] != pn) {
						} else {
								for (j = 1; j < i; j++) {
									field[enableFieldPathY - j][enableFieldPathX - j] = pn;
								}
								break outer;
						}		
					}
				} 
				} catch (Exception e) {
				}
			try {
				outer:
				if (field[enableFieldPathY - 1][enableFieldPathX] != pn && field[enableFieldPathY - 1][enableFieldPathX] != 0) {				//上側計算
					for (i = 2;(field[enableFieldPathY - i][enableFieldPathX] != 0) && i < SIZE; i++) {	
						if(field[enableFieldPathY - i][enableFieldPathX] != 0 && field[enableFieldPathY - i][enableFieldPathX] != pn) {
						} else {
								for (j = 1; j < i; j++) {
									field[enableFieldPathY - j][enableFieldPathX] = pn;
								}
								break outer;
						}		
					}
				} 
				} catch (Exception e) {
				}
			try {
				outer:
				if (field[enableFieldPathY - 1][enableFieldPathX + 1] != pn && field[enableFieldPathY - 1][enableFieldPathX + 1] != 0) {				//右上側計算
					for (i = 2;(field[enableFieldPathY - i][enableFieldPathX + i] != 0) && i < SIZE; i++) {	
						if(field[enableFieldPathY - i][enableFieldPathX + i] != 0 && field[enableFieldPathY - i][enableFieldPathX + i] != pn) {
						} else {
								for (j = 1; j < i; j++) {
									field[enableFieldPathY - j][enableFieldPathX + j] = pn;
								}
								break outer;
						}		
					}
				} 
				} catch (Exception e) {
				}
			try {
				outer:
				if (field[enableFieldPathY + 1][enableFieldPathX - 1] != pn && field[enableFieldPathY + 1][enableFieldPathX - 1] != 0) {				//左下側計算
					for (i = 2;(field[enableFieldPathY + i][enableFieldPathX - i] != 0) && i < SIZE; i++) {	
						if(field[enableFieldPathY + i][enableFieldPathX - i] != 0 && field[enableFieldPathY + i][enableFieldPathX - i] != pn) {
						} else {
								for (j = 1; j < i; j++) {
									field[enableFieldPathY + j][enableFieldPathX - j] = pn;
								}
								break outer;
						}		
					}
				} 
				} catch (Exception e) {
				}
			try {
				outer:
				if (field[enableFieldPathY + 1][enableFieldPathX] != pn && field[enableFieldPathY + 1][enableFieldPathX] != 0) {				//下側計算
					for (i = 2;(field[enableFieldPathY + i][enableFieldPathX] != 0) && i < SIZE; i++) {	
						if(field[enableFieldPathY + i][enableFieldPathX] != 0 && field[enableFieldPathY + i][enableFieldPathX] != pn) {
						} else {
								for (j = 1; j < i; ++j) {
									field[enableFieldPathY + j][enableFieldPathX] = pn;
								}
								break outer;
						}		
					}
				} 
				} catch (Exception e) {
				}
			try {
				outer:
				if (field[enableFieldPathY + 1][enableFieldPathX + 1] != pn && field[enableFieldPathY + 1][enableFieldPathX + 1] != 0) {				//右下側計算
					for (i = 2;(field[enableFieldPathY + i][enableFieldPathX + i] != 0) && i < SIZE; i++) {	
						if(field[enableFieldPathY + i][enableFieldPathX + i] != 0 && field[enableFieldPathY + i][enableFieldPathX + i] != pn) {
						} else {
								for (j = 1; j < i; j++) {
									field[enableFieldPathY + j][enableFieldPathX + j] = pn;
								}
								break outer;
						}		
					}
				} 
				} catch (Exception e) {
				}
		}	
	/****************************反転計算・配列へ格納(末尾)***************************/
		
	/****************************NPC座標指定メソッド(先頭)***************************/
		public static void npcDecide(int pn) {
			int enablelistNpcChoice = (int)(Math.random() * (fieldSearch(pn).size() - 1));
			String npcDecideField = fieldSearch(pn).get(enablelistNpcChoice);
			int devide = npcDecideField.indexOf(",");
			String enableFieldPathYs = npcDecideField.substring(0, devide);
			String enableFieldPathXs = npcDecideField.substring(devide + 1);
			enableFieldPathY = Integer.parseInt(enableFieldPathYs);
			enableFieldPathX = Integer.parseInt(enableFieldPathXs);
		}
	/****************************NPC座標指定メソッド(末尾)***************************/

	/****************************空き座標有無確認メソッド(先頭)***************************/
		public static boolean fieldSpaceCheck() {
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					if(field[i][j] == 0){
						return true;
					}
				}
			}
			return false;
		}
	/****************************空き座標有無確認メソッド(末尾)***************************/
		
}
