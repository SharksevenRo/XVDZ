package com.xiaov.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LogBuilder {

	private static final String path = LogBuilder.class.getClassLoader()
			.getResource("log.txt").getPath();

	public static void writeToLog(String message) {

		if (message != null) {
			if (message != null && !message.equals("")) {
				synchronized (LogBuilder.class) {
					FileWriter fw = null;
					File f = null;
					PrintWriter pw = null;
					try {

						// 如果文件存在，则追加内容；如果文件不存在，则创建文件
						f = new File(path);
						fw = new FileWriter(f, true);
						pw = new PrintWriter(fw);
						pw.println("\r\n" + new Date() + ":" + message);
						pw.flush();
						fw.flush();

					} catch (Exception e) {
						e.printStackTrace();
					} finally {

						if (fw != null) {
							try {
								fw.close();
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								fw = null;
							}
						}
						if (pw != null) {
							try {
								pw.close();
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								pw = null;
							}
						}
					}

				}
			}
		}
	}
}
