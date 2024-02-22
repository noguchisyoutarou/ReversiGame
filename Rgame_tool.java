import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Rgame_tool { 
	/*ゲーム後の結果計算、結果表示*/
	public static void fieldCount(int[][] array) {
	int a = 0;
	int b = 0;
		for (int y = 0; y < array.length; y++) {
			for (int x = 0; x < array[y].length; x++) {
				if (array[y][x] == 1) {
					a = a + 1;
				} else if (array[y][x] == 2) {
					b = b + 1;
				} else {
				}
			}
		}
		System.out.println("先攻は" + a + "点、後攻は" + b + "点");
		if (a > b) {
			System.out.println("先攻の勝ちです。");
		} else if (a < b) {
			System.out.println("後攻の勝ちです。");
		} else {
			System.out.println("引き分けです。");
		}				
	}

	/*ゲーム中の盤面表示*/
	public static void fieldPrint(int[][] array) {
		int a = 0;
		int b = 0;
		System.out.printf("%-3s%-3d%-3d%-3d%-3d%-3d%-3d%-3d%-3d%n", " ", 0, 1, 2, 3, 4, 5, 6, 7);
		char fieldY = 'A';
		for (int y = 0; y < array.length; y++) {
			System.out.printf("%-3c", fieldY + y);
			for (int x = 0; x < array[y].length; x++) {
				if (array[y][x] == 0) {					//表示内容の置き換え
					System.out.printf("%-3c",'-');
				} else if (array[y][x] == 1) {
					System.out.printf("%-3c",'o');
					a = a + 1;
				} else if (array[y][x] == 2) {
					System.out.printf("%-3c",'x');
					b = b + 1;
				}
			}	
			System.out.println();
		}
		System.out.println("先攻=o:" + a +"　後攻=x:" + b);
	}	
	
	
	
	/*配置可能座標確認・格納*/
	public static ArrayList<String> fieldSearch(int[][] field, int pn) {
		int ya;
		int xa;
		int p = pn;
		ArrayList<String> enablelist = new ArrayList<String>(); 
		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[y].length; x++) {								
					ya = y;
					xa = x;
					try {
						if ((field[ya][xa - 1] != p && field[y][x - 1] != 0) && field[y][x] == 0)  {  //配列の左側を調査
							ya = y;
							xa = x;
							outer:
								for (int i = 2; i < 7; i++) {
									if (field[ya][xa - i] == p) {
									enablelist.add(y + ", "+ x);
									break outer;
									} else if (field[ya][xa - i] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[ya][xa + 1] != p && field[y][x + 1] != 0) && field[y][x] == 0)  {  //配列の右側を調査
							ya = y;
							xa = x;
							outer:
								for (int i = 2; i < 7; i++) {
									if (field[ya][xa + i] == p) {
									enablelist.add(y + ", "+ x);
									break outer;
									} else if (field[ya][xa + i] == 0) {
										break outer;
									} else { 
									}
								}	
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[ya - 1][xa - 1] != p && field[y - 1][x - 1] != 0) && field[y][x] == 0)  {  //配列の左上側を調査
							ya = y;
							xa = x;
							outer:
								for (int i = 2; i < 7; i++) {
									if (field[ya - i][xa - i] == p) {
									enablelist.add(y + ", "+ x);
									break outer;
									} else if (field[ya - i][xa - i] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[ya - 1][xa] != p && field[y - 1][x] != 0) && field[y][x] == 0)  {  //配列の上側を調査
							ya = y;
							xa = x;
							outer:
								for (int i = 2; i < 7; i++) {
									if (field[ya - i][xa] == p) {
									enablelist.add(y + ", "+ x);
									break outer;
									} else if (field[ya - i][xa] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[ya - 1][xa + 1] != p && field[y - 1][x + 1] != 0) && field[y][x] == 0)  {  //配列の右上側を調査
							ya = y;
							xa = x;
							outer:
								for (int i = 2; i < 7; i++) {
									if (field[ya - i][xa + i] == p) {
									enablelist.add(y + ", "+ x);
									break outer;
									} else if (field[ya - i][xa + i] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[ya + 1][xa - 1] != p && field[y + 1][x - 1] != 0) && field[y][x] == 0)  {  //配列の左下側を調査
							ya = y;
							xa = x;
							outer:
								for (int i = 2; i < 7; i++) {
									if (field[ya + i][xa - i] == p) {
									enablelist.add(y + ", "+ x);
									break outer;
									} else if (field[ya + i][xa - i] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[ya + 1][xa] != p && field[y + 1][x] != 0) && field[y][x] == 0)  {  //配列の下側を調査
							ya = y;
							xa = x;
							outer:
								for (int i = 2; i < 7; i++) {
									if (field[ya + i][xa] == p) {
									enablelist.add(y + ", "+ x);
									break outer;
									} else if (field[ya + i][xa] == 0) {
										break outer;
									} else { 
								}
							}
						} else {
						}
					} catch (Exception e) {
					}
					try {
						if ((field[ya + 1][xa + 1] != p && field[y + 1][x + 1] != 0) && field[y][x] == 0)  {  //配列の右上側を調査
							ya = y;
							xa = x;
							outer:
								for (int i = 2; i < 7; i++) {
									if (field[ya + i][xa + i] == p) {
									enablelist.add(y + ", "+ x);
									break outer;
									} else if (field[ya + i][xa + i] == 0) {
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
		/*　配置可能座標格納配列　確認用
		for (int el = 0; el < enablelist.size(); el++) {
			System.out.println(enablelist.get(el));			//配列位置を表示するので座標位置としては+1すること
		}
		*/
		return enablelist;
	}
	
	/*入力値と配置可能座標比較*/
	public static boolean inputCheck (String enableYX, ArrayList<String> enablelist) {
		String enableYXs = enableYX;
		return enablelist.contains(enableYXs);
	}
	
	 /*反転計算・配列へ格納*/
	public static void reverseCulculate (int[][] field,int p, int lineYi, int lineXi) {
		int stone = p;
		field[lineYi][lineXi] = stone;		//配置座標へ代入
		int i;//繰り返し用変数
		int n;//繰り返し用変数
		try {
		outer:
		if (field[lineYi][lineXi - 1] != p && field[lineYi][lineXi - 1] != 0) {				//左側計算
			for (i = 2;(field[lineYi][lineXi - i] != 0) && i < 7; i++) {	
				if(field[lineYi][lineXi - i] != 0 && field[lineYi][lineXi - i] != p) {
				} else {
						for (n = 1; n < i; n++) {
							field[lineYi][lineXi - n] = p;
						}
						break outer;
				}		
			}
		} 
		} catch (Exception e) {
		}
		try {
			outer:
			if (field[lineYi][lineXi + 1] != p && field[lineYi][lineXi + 1] != 0) {				//右側計算
				for (i = 2;(field[lineYi][lineXi + i] != 0) && i < 7; i++) {	
					if(field[lineYi][lineXi + i] != 0 && field[lineYi][lineXi + i] != p) {
					} else {
							for (n = 1; n < i; n++) {
								field[lineYi][lineXi + n] = p;
							}
							break outer;
					}		
				}
			} 
			} catch (Exception e) {
			}
		try {
			outer:
			if (field[lineYi - 1][lineXi - 1] != p && field[lineYi - 1][lineXi - 1] != 0) {				//左上側計算
				for (i = 2;(field[lineYi - i][lineXi - i] != 0) && i < 7; i++) {	
					if(field[lineYi - i][lineXi - i] != 0 && field[lineYi - i][lineXi - i] != p) {
					} else {
							for (n = 1; n < i; n++) {
								field[lineYi - n][lineXi - n] = p;
							}
							break outer;
					}		
				}
			} 
			} catch (Exception e) {
			}
		try {
			outer:
			if (field[lineYi - 1][lineXi] != p && field[lineYi - 1][lineXi] != 0) {				//上側計算
				for (i = 2;(field[lineYi - i][lineXi] != 0) && i < 7; i++) {	
					if(field[lineYi - i][lineXi] != 0 && field[lineYi - i][lineXi] != p) {
					} else {
							for (n = 1; n < i; n++) {
								field[lineYi - n][lineXi] = p;
							}
							break outer;
					}		
				}
			} 
			} catch (Exception e) {
			}
		try {
			outer:
			if (field[lineYi - 1][lineXi + 1] != p && field[lineYi - 1][lineXi + 1] != 0) {				//右上側計算
				for (i = 2;(field[lineYi - i][lineXi + 1] != 0) && i < 7; i++) {	
					if(field[lineYi - i][lineXi + i] != 0 && field[lineYi - i][lineXi + i] != p) {
					} else {
							for (n = 1; n < i; n++) {
								field[lineYi - n][lineXi + n] = p;
							}
							break outer;
					}		
				}
			} 
			} catch (Exception e) {
			}
		try {
			outer:
			if (field[lineYi + 1][lineXi - 1] != p && field[lineYi + 1][lineXi - 1] != 0) {				//左下側計算
				for (i = 2;(field[lineYi + i][lineXi - i] != 0) && i < 7; i++) {	
					if(field[lineYi + i][lineXi - i] != 0 && field[lineYi + i][lineXi - i] != p) {
					} else {
							for (n = 1; n < i; n++) {
								field[lineYi + n][lineXi - n] = p;
							}
							break outer;
					}		
				}
			} 
			} catch (Exception e) {
			}
		try {
			outer:
			if (field[lineYi + 1][lineXi] != p && field[lineYi + 1][lineXi] != 0) {				//下側計算
				for (i = 2;(field[lineYi + i][lineXi] != 0) && i < 7; i++) {	
					if(field[lineYi + i][lineXi] != 0 && field[lineYi + i][lineXi] != p) {
					} else {
							for (n = 1; n < i; ++n) {
								field[lineYi + n][lineXi] = p;
							}
							break outer;
					}		
				}
			} 
			} catch (Exception e) {
			}
		try {
			outer:
			if (field[lineYi + 1][lineXi + 1] != p && field[lineYi + 1][lineXi + 1] != 0) {				//右下側計算
				for (i = 2;(field[lineYi + i][lineXi + i] != 0) && i < 7; i++) {	
					if(field[lineYi + i][lineXi + i] != 0 && field[lineYi + i][lineXi + i] != p) {
					} else {
							for (n = 1; n < i; n++) {
								field[lineYi + n][lineXi + n] = p;
							}
							break outer;
					}		
				}
			} 
			} catch (Exception e) {
			}
	}
	
	/*NPC座標指定メソッド*/
	public static String npcDecide(ArrayList<String> enablelist) {
		int enablelistNpcChoice = (int)(Math.random() * (enablelist.size() - 1));
		String npcDecideField = enablelist.get(enablelistNpcChoice);
		return npcDecideField;
	}
}

	