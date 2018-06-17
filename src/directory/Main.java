package directory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		Main program = new Main();
		program.start();
	}

	private void start() throws IOException {
		System.out.println("親フォルダのアドレスを入力してください");
		System.out.println("(例) c:/start");
		String parent = input() + "/";
		System.out.println("お宝までのルートを4桁の数値で入力してください");
		String routeStr = input();
		int routeNum = Integer.parseInt(routeStr);
		int firstHitNum = routeNum / 1000;
		int secondHitNum = (routeNum % 1000) / 100;
		int thirdHitNum = (routeNum % 100) / 10;
		int fourthHitNum = routeNum % 10;
		makeDummies(parent);
		parent += firstHitNum + "/";
		makeDummies(parent);
		parent += secondHitNum + "/";
		makeDummies(parent);
		parent += thirdHitNum + "/";
		makeDummies(parent);
		parent += fourthHitNum + "/";
		String readmeText = "ここが最終地点";
		fileWrite(parent, "readme.txt", readmeText);
	}

	private void makeDummies(String parent) throws FileNotFoundException {
		//ダミーの生成
		for(int i = 0; i < 10; i++) {
			String directoryName = parent + i + "/";
			makeDirectory(directoryName);
		}
	}

	private void makeDirectory(String directoryName) throws FileNotFoundException {
		//フォルダの生成
		File newDirectory = new File(directoryName);
		if(newDirectory.exists()) {
			System.out.println("ディレクトリはすでに存在します");
		}
		else if(newDirectory.mkdir()) {
			System.out.println("ディレクトリを作成しました");
		}
		else {
			System.out.println("ディレクトリの作成に失敗しました");
			throw new FileNotFoundException();
		}
	}

	private void fileWrite(String directoryName, String fileName, String text) throws IOException {
		//ファイルの生成
		File file = new File(directoryName + fileName);
		try(
				FileWriter fileWriter = new FileWriter(file);
				)
		{
			fileWriter.write(text);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String input() throws IOException {
		//コンソール入力
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}
}